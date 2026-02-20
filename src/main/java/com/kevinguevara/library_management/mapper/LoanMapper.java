package com.kevinguevara.library_management.mapper;

import com.kevinguevara.library_management.model.*;
import com.kevinguevara.library_management.dto.loan.LoanRequestDTO;
import com.kevinguevara.library_management.dto.loan.LoanResponseDTO;

import org.springframework.stereotype.Component;

@Component
public class LoanMapper {
    public Loan toEntity(LoanRequestDTO requestDTO){
        if(requestDTO == null)
            return null;
        return Loan.builder()
            .bookId(requestDTO.getBookId())
            .accountId(requestDTO.getAccountId())
            .build();
    }

    public LoanResponseDTO toLoanResponseDTO(Loan loan){
        if(loan == null)
            return null;
        return LoanResponseDTO.builder()
            .loanId(loan.getLoanId())
            .bookId(loan.getBookId())
            .accountId(loan.getAccountId())
            .checkoutDate(loan.getCheckoutDate())
            .dueDate(loan.getDueDate())
            .returnDate(loan.getReturnDate())
            .loanStatus(loan.getStatus())
            .build();
    }
}
