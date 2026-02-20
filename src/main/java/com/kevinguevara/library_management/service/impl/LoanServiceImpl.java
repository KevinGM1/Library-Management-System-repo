package com.kevinguevara.library_management.service.impl;

import com.kevinguevara.library_management.dto.loan.LoanRequestDTO;
import com.kevinguevara.library_management.dto.loan.LoanResponseDTO;
import com.kevinguevara.library_management.mapper.LoanMapper;
import com.kevinguevara.library_management.model.Book;
import com.kevinguevara.library_management.model.Account;
import com.kevinguevara.library_management.model.Loan;
import com.kevinguevara.library_management.model.enums.LoanStatus;
import com.kevinguevara.library_management.repository.BookRepository;
import com.kevinguevara.library_management.repository.AccountRepository;
import com.kevinguevara.library_management.repository.LoanRepository;
import com.kevinguevara.library_management.service.LoanService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
//import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;
    private final AccountRepository accountRepository;
    private final BookRepository bookRepository;
    private final LoanMapper loanMapper;

    @Override
    @Transactional
    public LoanResponseDTO createLoan(LoanRequestDTO request){
        /*Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        accountRepository.findById(accountId).orElseThrow(() -> new IllegalArgumentException("Account not found"));

        //check if book is available
        if(book.getAvailableCopies() <= 0)
            throw new IllegalStateException("No copies available");

        Loan loan = Loan.builder()
                        .bookId(bookId)
                        .accountId(accountId)
                        .checkoutDate(LocalDate.now())
                        .dueDate(LocalDate.now().plusDays(14))
                        .build();

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        return loanRepository.save(loan); */

        Book book = bookRepository.findById(request.getBook().getBookId()).orElseThrow(
            ()-> new RuntimeException("Book not found"));
        accountRepository.findById(request.getAccount().getAccountId()).orElseThrow(
            () -> new RuntimeException("Account not found"));
        if(book.getAvailableCopies() <= 0){
            throw new RuntimeException("No copies available");
        }
        Loan loan = Loan.builder()
            .book(request.getBook())
            .account(request.getAccount())
            .checkoutDate(LocalDate.now())
            .dueDate(LocalDate.now().plusDays(14))
            .build();
        
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        Loan savedLoan = loanRepository.save(loan);
        return loanMapper.toResponseDTO(savedLoan);
       
    }
    @Override
    @Transactional
    public LoanResponseDTO returnBook(Long loanId){
        /*Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new IllegalArgumentException("Loan not found"));
        if(loan.getReturnDate() != null)
            throw new IllegalStateException("Loan already returned");
        loan.setReturnDate(LocalDate.now());

        Book book = bookRepository.findById(loan.getBookId()).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);
        return loanRepository.save(loan); */
        Loan loan = loanRepository.findById(loanId).orElseThrow(
            ()-> new RuntimeException("Loan not found"));
        if(loan.getStatus() == LoanStatus.RETURNED)
            throw new RuntimeException("Loan already returned");
       
        loan.setReturnDate(LocalDate.now());
        loan.setStatus(LoanStatus.RETURNED);

        Book book = loan.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        Loan returnedLoan = loanRepository.save(loan);
        return loanMapper.toResponseDTO(returnedLoan);
    }
    @Override
    public void renewBook(Long loanId){
        /*Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new IllegalArgumentException("Loan not found"));
        if(loan.getReturnDate() != null)
            throw new IllegalStateException("Loan already returned");
        //can only renew if there are available copies 
        Book book = bookRepository.findById(loan.getBookId()).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        if(book.getAvailableCopies() <= 0)
            throw new IllegalStateException("Can't renew book, no available copies");

        loan.setDueDate(loan.getDueDate().plusDays(14));
        loanRepository.save(loan); */
        Loan loan = loanRepository.findById(loanId).orElseThrow(
            ()-> new RuntimeException("Loan not found"));
        if(loan.getStatus() == LoanStatus.RETURNED)
            throw new RuntimeException("Loan already returned");

        Book book = loan.getBook();
        if(book.getAvailableCopies() <= 0)
            throw new RuntimeException("Cannot renew book if no copies available");

        loan.setDueDate(loan.getDueDate().plusDays(14));
        loanRepository.save(loan);
    }
     
    @Override
    public List<LoanResponseDTO> getAllLoans(){
        //return loanRepository.findByReturnDateIsNull();
        List<Loan> allLoans = loanRepository.findAll();
        return allLoans.stream().map(loanMapper::toResponseDTO).toList();
    }
    
    @Override
    public List<LoanResponseDTO> getLoansFromAccount(Long accountId){
       // return loanRepository.findByAccountId(accountId);
       List<Loan> allLoansFromAcc = loanRepository.findByAccountId(accountId);
       return allLoansFromAcc.stream().map(loanMapper::toResponseDTO).toList();
    } 
    
}
