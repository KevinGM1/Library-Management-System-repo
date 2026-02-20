package com.kevinguevara.library_management.dto.loan;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoanRequestDTO {
    @NotNull(message = "bookId is required")
    private Long bookId;
    @NotNull(message = "accountId is required")
    private Long accountId;
}
