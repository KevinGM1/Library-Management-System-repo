package com.kevinguevara.library_management.service.impl;

import com.kevinguevara.library_management.model.Book;
//import com.kevinguevara.library_management.model.Account;
import com.kevinguevara.library_management.model.Loan;
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

    @Override
    @Transactional
    public Loan createLoan(Long bookId, Long accountId){
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Book not found"));
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

        return loanRepository.save(loan);
    }
    @Override
    @Transactional
    public Loan returnBook(Long loanId){
        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new IllegalArgumentException("Loan not found"));
        if(loan.getReturnDate() != null)
            throw new IllegalStateException("Loan already returned");
        loan.setReturnDate(LocalDate.now());

        Book book = bookRepository.findById(loan.getBookId()).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);
        return loanRepository.save(loan);

    }
    @Override
    public void renewBook(Long loanId){
        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new IllegalArgumentException("Loan not found"));
        if(loan.getReturnDate() != null)
            throw new IllegalStateException("Loan already returned");
        //can only renew if there are available copies 
        Book book = bookRepository.findById(loan.getBookId()).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        if(book.getAvailableCopies() <= 0)
            throw new IllegalStateException("Can't renew book, no available copies");

        loan.setDueDate(loan.getDueDate().plusDays(14));
        loanRepository.save(loan);
    }
     
    @Override
    public List<Loan> getAllLoans(){
        return loanRepository.findByReturnDateIsNull();
    }
    
    @Override
    public List<Loan> getLoansFromAccount(Long accountId){
        return loanRepository.findByAccountId(accountId);
    } 
    
}
