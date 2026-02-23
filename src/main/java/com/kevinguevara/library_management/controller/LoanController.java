package com.kevinguevara.library_management.controller;

import com.kevinguevara.library_management.dto.loan.*;
import com.kevinguevara.library_management.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/loans")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;

    @PostMapping
    public ResponseEntity<LoanResponseDTO> createLoan(@RequestBody LoanRequestDTO request){
        LoanResponseDTO response = loanService.createLoan(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{loanId}/return")
    public ResponseEntity<LoanResponseDTO> returnBook(@PathVariable Long loanId){
        LoanResponseDTO response = loanService.returnBook(loanId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{loanId}/renew")
    public ResponseEntity<LoanResponseDTO> renew(@PathVariable Long loanId){
        LoanResponseDTO response = loanService.renewBook(loanId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<LoanResponseDTO>> getAllLoans(){
        return ResponseEntity.ok(loanService.getAllLoans());
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<LoanResponseDTO>> getLoansFromAccount(@PathVariable Long accountId){
        return ResponseEntity.ok(loanService.getLoansFromAccount(accountId));
    }
   

}
