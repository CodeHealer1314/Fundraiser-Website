package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.UserAccount;
import com.fundraiser.backend.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateUserAccountController {

    private final UserAccountRepository userAccountRepository;

    // Loads a single account by ID
    public UserAccount loadAccount(String accountId) {
        Optional<UserAccount> accountOpt = userAccountRepository.findById(accountId);
        return accountOpt.orElse(null);
    }

    // Updates an existing account
    public boolean updateAccount(String accountId, Object data) {
        if (!(data instanceof Map)) return false;

        Map<?, ?> map = (Map<?, ?>) data;
        String username = (String) map.get("username");
        String email = (String) map.get("email");
        String role = (String) map.get("role");

        // Validate data
        if (username == null || username.isEmpty() ||
                email == null || email.isEmpty() ||
                role == null || role.isEmpty()) {
            return false;
        }

        // Find existing account
        Optional<UserAccount> accountOpt = userAccountRepository.findById(accountId);
        if (accountOpt.isEmpty()) {
            return false;
        }

        // Update account fields
        UserAccount account = accountOpt.get();
        account.setUsername(username);
        account.setEmail(email);
        account.setRole(role);

        // Save updated account
        userAccountRepository.save(account);
        return true;
    }
}