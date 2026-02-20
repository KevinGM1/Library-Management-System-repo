package com.kevinguevara.library_management.service;

import com.kevinguevara.library_management.dto.account.AccountRequestDTO;
import com.kevinguevara.library_management.dto.account.AccountResponseDTO;
//import com.kevinguevara.library_management.model.Account;

public interface AccountService {
    AccountResponseDTO createAccount(AccountRequestDTO request);

    void deleteAccount(Long accountId);
}
