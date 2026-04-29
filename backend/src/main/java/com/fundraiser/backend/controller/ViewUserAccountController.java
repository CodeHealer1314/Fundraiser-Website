package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.UserAccount;
import com.fundraiser.backend.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViewUserAccountController {

    private final UserAccountRepository userAccountRepository;

    // Retrieves all user accounts from database
    public List<UserAccount> loadAccounts() {
        return userAccountRepository.findAll();
    }
}