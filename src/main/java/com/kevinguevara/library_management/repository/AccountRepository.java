package com.kevinguevara.library_management.repository;

import com.kevinguevara.library_management.model.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("FROM Account WHERE email = :email")
    Account findbyEmail(@Param("email") String email);
    
}
