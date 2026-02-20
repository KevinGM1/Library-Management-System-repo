package com.kevinguevara.library_management.dto.loan;

import com.kevinguevara.library_management.model.Account;
import com.kevinguevara.library_management.model.Book;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoanRequestDTO {
    @NotNull(message = "bookId is required")
    private Book book;
    @NotNull(message = "accountId is required")
    private Account account;
}
