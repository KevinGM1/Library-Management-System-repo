package com.kevinguevara.library_management.dto.loan;

import java.time.LocalDate;

import com.kevinguevara.library_management.model.Account;
import com.kevinguevara.library_management.model.Book;
import com.kevinguevara.library_management.model.enums.LoanStatus;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LoanResponseDTO {
    Long loanId;
    Book book;
    Account account;
    LocalDate checkoutDate;
    LocalDate dueDate;
    LocalDate returnDate;
    LoanStatus loanStatus;
}
