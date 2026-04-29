package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.ViewUserAccountController;
import com.fundraiser.backend.entity.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserAccountListPage {

    private final ViewUserAccountController viewUserAccountController;

    // Empty implementation - will implement in Step 4
    @GetMapping("/accounts")
    public ResponseEntity<?> loadAccounts() {
        List<UserAccount> accounts = viewUserAccountController.loadAccounts();

        // Step 6a: no accounts found
        if (accounts.isEmpty()) {
            return ResponseEntity.ok(Map.of("message", "No user accounts found"));
        }

        // Step 6: renderAccountList(results)
        return ResponseEntity.ok(accounts);
    }
}