package com.kevinguevara.library_management.service;

import com.kevinguevara.library_management.model.Loan;

import java.util.List;

public interface LoanService {

    public void createLoan();

    public void returnBook();

    public void renewBook();

    public List<Loan> getAllLoans();
    
    public List<Loan> getLoansFromAccount(Long accountId);
}
