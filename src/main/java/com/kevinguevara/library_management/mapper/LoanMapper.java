package com.kevinguevara.library_management.mapper;

import com.kevinguevara.library_management.model.*;
//import com.kevinguevara.library_management.dto.loan.LoanRequestDTO;
import com.kevinguevara.library_management.dto.loan.LoanResponseDTO;

import org.springframework.stereotype.Component;

@Component
public class LoanMapper {
    //public Loan toEntity(LoanRequestDTO requestDTO){
        /*if(requestDTO == null)
            return null;
        return Loan.builder()
            .book(requestDTO.getBook())
            .account(requestDTO.getAccount())
            .build(); */
        //Loan loan = new Loan();


    //}

    public LoanResponseDTO toResponseDTO(Loan loan){
        /*if(loan == null)
            return null;
        return LoanResponseDTO.builder()
            .loanId(loan.getLoanId())
            .book(loan.getBook())
            .account(loan.getAccount())
            .checkoutDate(loan.getCheckoutDate())
            .dueDate(loan.getDueDate())
            .returnDate(loan.getReturnDate())
            .loanStatus(loan.getStatus())
            .build(); */
        return new LoanResponseDTO(
            loan.getLoanId(), 
            loan.getBook().getBookId(),
            loan.getAccount().getAccountId(),
            loan.getCheckoutDate(),
            loan.getDueDate(),
            loan.getReturnDate(),
            loan.getStatus()
        );
        
    }
}
