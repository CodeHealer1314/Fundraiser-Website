package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.UserAccount;
import com.fundraiser.backend.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CreateUserAccountController {

    private final UserAccountRepository userAccountRepository;

    // Validates that all required fields are present
    public boolean validateAccount(Object data) {
        if (!(data instanceof Map)) return false;

        Map<?, ?> map = (Map<?, ?>) data;
        String username = (String) map.get("username");
        String email = (String) map.get("email");
        String password = (String) map.get("password");
        String role = (String) map.get("role");

        return username != null && !username.isEmpty() &&
                email != null && !email.isEmpty() &&
                password != null && !password.isEmpty() &&
                role != null && !role.isEmpty();
    }

    // Creates and saves the user account
    public UserAccount createAccount(Object data) {
        if (!validateAccount(data)) {
            return null;
        }

        Map<?, ?> map = (Map<?, ?>) data;

        // Create new account
        UserAccount account = new UserAccount();

        // Auto generate ID and timestamp
        account.refreshAuto();

        // Set account details
        account.setUsername((String) map.get("username"));
        account.setEmail((String) map.get("email"));
        account.setRole((String) map.get("role"));

        // Hash password before saving
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        account.setPassword(encoder.encode((String) map.get("password")));

        // Save to database
        return userAccountRepository.save(account);
    }
}