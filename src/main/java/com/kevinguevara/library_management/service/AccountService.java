package com.kevinguevara.library_management.service;

import com.kevinguevara.library_management.model.Account;

public interface AccountService {
    Account createAccount(Account account);

    void deleteAccount(Long accountId);
}
