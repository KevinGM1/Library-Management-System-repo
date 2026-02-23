package com.kevinguevara.library_management.service;

import com.kevinguevara.library_management.dto.loan.LoanRequestDTO;
import com.kevinguevara.library_management.dto.loan.LoanResponseDTO;
//import com.kevinguevara.library_management.model.Loan;

import java.util.List;

public interface LoanService {

    LoanResponseDTO createLoan(LoanRequestDTO request);

    LoanResponseDTO returnBook(Long loanId);

    LoanResponseDTO renewBook(Long loanId);

    List<LoanResponseDTO> getAllLoans();
    
    List<LoanResponseDTO> getLoansFromAccount(Long accountId);
}
