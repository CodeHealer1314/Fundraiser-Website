package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.CreateUserAccountController;
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
public class CreateUserAccountPage {

    private final CreateUserAccountController createUserAccountController;

    // Empty implementation - will implement in Step 4
    @PostMapping("/accounts")
    public ResponseEntity<?> handleSubmit(@RequestBody AccountRequest request) {
        // Convert request to map
        Map<String, String> data = new java.util.HashMap<>();
        data.put("username", request.getUsername());
        data.put("email", request.getEmail());
        data.put("password", request.getPassword());
        data.put("role", request.getRole());

        // Step 3: validateAccount(data)
        if (!createUserAccountController.validateAccount(data)) {
            return ResponseEntity
                    .status(400)
                    .body(Map.of("message", "Validation failed - please check all fields"));
        }

        // Step 5: createAccount(data)
        UserAccount account = createUserAccountController.createAccount(data);

        if (account == null) {
            return ResponseEntity
                    .status(500)
                    .body(Map.of("message", "Failed to create account"));
        }

        // Step 9: setSuccessMessage("Account created successfully")
        return ResponseEntity.ok(Map.of(
                "message", "Account created successfully",
                "accountId", account.getAccountId(),
                "username", account.getUsername(),
                "email", account.getEmail(),
                "role", account.getRole()
        ));
    }

    // DTO - holds incoming request data
    @Data
    public static class AccountRequest {
        private String username;
        private String email;
        private String password;
        private String role;
    }
}