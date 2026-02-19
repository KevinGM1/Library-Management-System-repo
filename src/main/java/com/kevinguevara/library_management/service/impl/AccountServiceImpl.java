package com.kevinguevara.library_management.service.impl;

import com.kevinguevara.library_management.model.Account;
import com.kevinguevara.library_management.repository.AccountRepository;
import com.kevinguevara.library_management.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account){
        Optional<Account> optionalAccount = accountRepository.findByEmail(account.getEmail());
        if(optionalAccount.isPresent()){
            throw new IllegalArgumentException("Account already exists");
        }
        return accountRepository.save(account);
    }
    @Override
    public void deleteAccount(Long accountId){
        if(accountRepository.existsById(accountId)){
            throw new IllegalArgumentException("Book not found with id: " + accountId);
        }
        accountRepository.deleteById(accountId);
    }



}
