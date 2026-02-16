package com.kevinguevara.library_management.model;

import com.kevinguevara.library_management.model.enums.LoanStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Table(
    name = "loans"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Loan{
    @Column (name = "loanId")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bookId", nullable = false)
    private Long bookId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "accountId", nullable = false)
    private Long accountId;

    @Column(name = "checkoutDate", nullable = false)
    private LocalDate checkoutDate;
   
    @Column(name = "dueDate", nullable = false)
    private LocalDate dueDate;

    @Column(name = "returnDate", nullable = false)
    private LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "loanStatus", nullable = false)
    private LoanStatus status;

    @PrePersist
    protected void onCreate(){
        this.checkoutDate = LocalDate.now();
        this.dueDate = this.checkoutDate.plusDays(14);
        this.status = LoanStatus.ACTIVE;
    }

    public boolean isOverdue(){
        if(this.status == LoanStatus.ACTIVE && LocalDate.now().isAfter(this.dueDate))
            return true;
        else
            return false;
    }

    public void markReturned(){
        if(this.status == LoanStatus.RETURNED){
            throw new IllegalStateException("Loan already returned!");
        }
        this.returnDate = LocalDate.now();
        this.status = LoanStatus.RETURNED;
    }


}