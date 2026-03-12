package com.kevinguevara.library_management.dto.loan;

import java.time.LocalDate;

//import com.kevinguevara.library_management.model.Account;
//import com.kevinguevara.library_management.model.Book;
import com.kevinguevara.library_management.model.enums.LoanStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanResponseDTO {
    private Long loanId;
    private Long bookId;
    private Long accountId;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private LoanStatus loanStatus;
}
