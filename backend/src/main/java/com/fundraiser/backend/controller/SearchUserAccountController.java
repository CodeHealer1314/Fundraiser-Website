package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.UserAccount;
import com.fundraiser.backend.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchUserAccountController {

    private final UserAccountRepository userAccountRepository;

    // Searches accounts by keyword
    public List<UserAccount> searchAccounts(String keyword) {
        // Empty keyword → return empty list
        if (keyword == null || keyword.isEmpty()) {
            return new ArrayList<>();
        }

        return userAccountRepository.searchAccounts(keyword);
    }
}
