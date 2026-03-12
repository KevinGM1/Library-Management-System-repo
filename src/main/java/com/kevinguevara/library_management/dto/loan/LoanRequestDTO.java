package com.kevinguevara.library_management.dto.loan;

//import com.kevinguevara.library_management.model.Account;
//import com.kevinguevara.library_management.model.Book;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanRequestDTO {
    @NotNull(message = "bookId is required")
    private Long bookId;
    @NotNull(message = "accountId is required")
    private Long accountId;
}
