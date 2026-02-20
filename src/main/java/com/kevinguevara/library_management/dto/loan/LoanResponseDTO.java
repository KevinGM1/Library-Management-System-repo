package com.kevinguevara.library_management.dto.loan;

import java.time.LocalDate;
import com.kevinguevara.library_management.model.enums.LoanStatus;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LoanResponseDTO {
    Long loanId;
    Long bookId;
    Long accountId;
    LocalDate checkoutDate;
    LocalDate dueDate;
    LocalDate returnDate;
    LoanStatus loanStatus;
}
