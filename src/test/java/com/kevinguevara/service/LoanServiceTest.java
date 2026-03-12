package com.kevinguevara.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.kevinguevara.library_management.dto.loan.LoanRequestDTO;
import com.kevinguevara.library_management.dto.loan.LoanResponseDTO;
import com.kevinguevara.library_management.mapper.LoanMapper;
import com.kevinguevara.library_management.model.Account;
import com.kevinguevara.library_management.model.Book;
import com.kevinguevara.library_management.model.Loan;
import com.kevinguevara.library_management.model.enums.LoanStatus;
import com.kevinguevara.library_management.repository.AccountRepository;
import com.kevinguevara.library_management.repository.BookRepository;
import com.kevinguevara.library_management.repository.LoanRepository;
import com.kevinguevara.library_management.service.impl.LoanServiceImpl;

public class LoanServiceTest {
    @Mock
    private LoanRepository loanRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    LoanMapper loanMapper;

    @InjectMocks
    private LoanServiceImpl loanService;

    private Book book;
    private Account account;
    private Loan loan;
    private LoanRequestDTO requestDTO;
    private LoanResponseDTO responseDTO;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        book = new Book();
        book.setBookId(1L);
        book.setTitle("Harry Potter");
        book.setAvailableCopies(5);
       
        account = new Account();
        account.setAccountId(2L);
        account.setName("Kevin");
       
        loan = new Loan();
        loan.setLoanId(10L);
        loan.setBook(book);
        loan.setAccount(account);
        loan.setCheckoutDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusDays(14));
        loan.setStatus(LoanStatus.ACTIVE);

        requestDTO = new LoanRequestDTO(1L, 2L);

        responseDTO = new LoanResponseDTO(
                10L,
                1L,
                2L,
                loan.getCheckoutDate(),
                loan.getDueDate(),
                null,
                LoanStatus.ACTIVE);
        }

    @Test
    void testCreateLoan(){
        
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(account));
        when(loanRepository.save(any(Loan.class))).thenReturn(loan);
        when(loanMapper.toResponseDTO(loan)).thenReturn(responseDTO);

        LoanResponseDTO result = loanService.createLoan(requestDTO);

        assertNotNull(result);
        assertEquals(1L, result.getBookId());

        verify(loanRepository).save(any(Loan.class));
    }

    @Test
    void testCheckoutBook_BookUnavailable() {

        book.setAvailableCopies(0);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(account));

        assertThrows(RuntimeException.class, () -> {loanService.createLoan(requestDTO);});

        verify(loanRepository, never()).save(any());
    }   
    @Test
    void testReturnBook() {

        when(loanRepository.findById(10L)).thenReturn(Optional.of(loan));
        when(loanMapper.toResponseDTO(any(Loan.class))).thenReturn(responseDTO);

        LoanResponseDTO result = loanService.returnBook(10L);
    
        assertNotNull(result);
       
        assertEquals(LoanStatus.RETURNED, loan.getStatus());
        assertNotNull(loan.getReturnDate());

        verify(loanRepository).findById(10L);
        verify(loanMapper).toResponseDTO(loan);
    }

    @Test
    void testRenewLoan() {

        when(loanRepository.findById(10L)).thenReturn(Optional.of(loan));
        when(loanMapper.toResponseDTO(loan)).thenReturn(responseDTO);

        LocalDate oldDueDate = loan.getDueDate();

        LoanResponseDTO result = loanService.renewBook(10L);

        assertNotNull(result);
        
        assertTrue(loan.getDueDate().isAfter(oldDueDate));
        verify(loanMapper).toResponseDTO(loan);
    }
    
    @Test
    void testGetLoansByAccount() {  

        when(accountRepository.findById(2L)).thenReturn(Optional.of(account));
        when(loanRepository.findByAccountId(account.getAccountId())).thenReturn(List.of(loan));
        when(loanMapper.toResponseDTO(loan)).thenReturn(responseDTO);

        List<LoanResponseDTO> result = loanService.getLoansFromAccount(2L);

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getBookId());
    }

    @Test
    void testGetAllLoans() {

        when(loanRepository.findAll()).thenReturn(List.of(loan));
        when(loanMapper.toResponseDTO(loan)).thenReturn(responseDTO);

        List<LoanResponseDTO> result = loanService.getAllLoans();

        assertEquals(1, result.size());
    }



}