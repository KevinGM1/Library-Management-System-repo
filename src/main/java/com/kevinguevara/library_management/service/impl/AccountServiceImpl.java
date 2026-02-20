package com.kevinguevara.library_management.service.impl;

import com.kevinguevara.library_management.dto.account.AccountRequestDTO;
import com.kevinguevara.library_management.dto.account.AccountResponseDTO;
import com.kevinguevara.library_management.mapper.AccountMapper;
import com.kevinguevara.library_management.model.Account;
import com.kevinguevara.library_management.repository.AccountRepository;
import com.kevinguevara.library_management.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountResponseDTO createAccount(AccountRequestDTO request){
        /*Optional<Account> optionalAccount = accountRepository.findByEmail(account.getEmail());
        if(optionalAccount.isPresent()){
            throw new IllegalArgumentException("Account already exists");
        }
        return accountRepository.save(account); */
        if(accountRepository.findByEmail(request.getEmail()).isPresent())
            throw new IllegalArgumentException("Account already exists");

        Account account = accountMapper.toEntity(request);
        Account saved = accountRepository.save(account);
        return accountMapper.toResponseDTO(saved);

    }
    @Override
    public void deleteAccount(Long accountId){
        /*if(accountRepository.existsById(accountId)){
            throw new IllegalArgumentException("Book not found with id: " + accountId);
        }
        accountRepository.deleteById(accountId); */
        if(!accountRepository.existsById(accountId)){
            throw new IllegalArgumentException("Account not found with id: " + accountId);
        }
        accountRepository.deleteById(accountId);
    }



}
