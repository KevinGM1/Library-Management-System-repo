package com.kevinguevara.library_management.repository;

import com.kevinguevara.library_management.model.Loan;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

public interface LoanRepository extends JpaRepository<Loan, Long>{
    
    //@Query("FROM Loan WHERE accountId = :accountId")
    //List<Loan> findByAccountId(@Param("accountId") Long accountId);
    List<Loan> findByAccountId(Long accountId);

    List<Loan> findByReturnDateIsNull();

    
}
