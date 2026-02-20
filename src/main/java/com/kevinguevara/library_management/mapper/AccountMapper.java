package com.kevinguevara.library_management.mapper;

import com.kevinguevara.library_management.model.Account;
import com.kevinguevara.library_management.dto.account.*;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public Account toEntity(AccountRequestDTO requestDTO){
        if(requestDTO == null)
            return null;

        return Account.builder()
            .name(requestDTO.getName())
            .email(requestDTO.getEmail())
            .build();
    }

    public AccountResponseDTO toResponseDTO(Account account){
        if(account == null)
            return null;
        return AccountResponseDTO.builder()
            .accountId(account.getAccountId())
            .name(account.getName())
            .email(account.getEmail())
            .build();
    }
    
}
