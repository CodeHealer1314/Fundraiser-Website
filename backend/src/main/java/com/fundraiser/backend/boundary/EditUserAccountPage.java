package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.UpdateUserAccountController;
import com.fundraiser.backend.entity.UserAccount;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EditUserAccountPage {

    private final UpdateUserAccountController updateUserAccountController;

    // Empty implementation - will implement in Step 4
    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<?> loadAccount(@PathVariable String accountId) {
        // Step 2: loadAccount(accountId)
        UserAccount account = updateUserAccountController.loadAccount(accountId);

        if (account == null) {
            return ResponseEntity
                    .status(404)
                    .body(Map.of("message", "Account not found"));
        }

        // Step 5: renderAccount(account)
        return ResponseEntity.ok(account);
    }

    @PutMapping("/accounts/{accountId}")
    public ResponseEntity<?> updateAccount(
            @PathVariable String accountId,
            @RequestBody AccountUpdateRequest request) {

        // Convert request to map
        Map<String, String> data = new java.util.HashMap<>();
        data.put("username", request.getUsername());
        data.put("email", request.getEmail());
        data.put("role", request.getRole());

        // Step 8: updateAccount(accountId, data)
        boolean result = updateUserAccountController.updateAccount(accountId, data);

        if (!result) {
            // Step 12a: setErrorMessage("Update failed")
            return ResponseEntity
                    .status(400)
                    .body(Map.of("message", "Update failed"));
        }

        // Step 12: setSuccessMessage("Account updated successfully")
        return ResponseEntity.ok(Map.of("message", "Account updated successfully"));
    }

    // DTO - holds incoming request data
    @Data
    public static class AccountUpdateRequest {
        private String username;
        private String email;
        private String role;
    }
}
