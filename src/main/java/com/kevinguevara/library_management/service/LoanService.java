package com.kevinguevara.library_management.service;

import com.kevinguevara.library_management.model.Loan;

import java.util.List;

public interface LoanService {

    Loan createLoan(Long bookId, Long accountId);

    Loan returnBook(Long loanId);

    void renewBook(Long loanId);

    List<Loan> getAllLoans();
    
    List<Loan> getLoansFromAccount(Long accountId);
}
