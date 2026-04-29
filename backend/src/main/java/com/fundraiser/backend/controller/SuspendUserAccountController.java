package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.UserAccount;
import com.fundraiser.backend.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SuspendUserAccountController {

    private final UserAccountRepository userAccountRepository;

    // Suspends a user account by ID
    public boolean handleSuspend(String accountId) {
        Optional<UserAccount> accountOpt = userAccountRepository.findById(accountId);

        // Account not found → return false
        if (accountOpt.isEmpty()) {
            return false;
        }

        UserAccount account = accountOpt.get();

        // Already suspended → return false
        if (account.isSuspended()) {
            return false;
        }

        // Suspend the account
        account.setSuspended(true);
        userAccountRepository.save(account);
        return true;
    }
}