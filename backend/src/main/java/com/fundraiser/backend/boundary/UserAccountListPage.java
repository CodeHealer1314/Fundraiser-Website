package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.SuspendUserAccountController;
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
    private final SuspendUserAccountController suspendUserAccountController;

    @GetMapping("/accounts")
    public ResponseEntity<?> loadAccounts() {
        List<UserAccount> accounts = viewUserAccountController.loadAccounts();

        if (accounts.isEmpty()) {
            return ResponseEntity.ok(Map.of("message", "No user accounts found"));
        }

        return ResponseEntity.ok(accounts);
    }

    @PatchMapping("/accounts/{accountId}/suspend")
    public ResponseEntity<?> suspendAccount(@PathVariable String accountId) {
        // Step 4: handleSuspend(accountId)
        boolean result = suspendUserAccountController.handleSuspend(accountId);

        if (!result) {
            return ResponseEntity
                    .status(400)
                    .body(Map.of("message", "Suspension failed - account not found or already suspended"));
        }

        // Step 8: setSuccessMessage("Account suspended successfully")
        return ResponseEntity.ok(Map.of("message", "Account suspended successfully"));
    }
}