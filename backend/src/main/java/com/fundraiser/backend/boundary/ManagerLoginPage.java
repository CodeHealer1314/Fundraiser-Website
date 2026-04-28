package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.ManagerLoginController;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ManagerLoginPage {

    private final ManagerLoginController managerLoginController;

    // POST /api/admin/login
    // BCE boundary entry point — maps to sequence diagram step 3 "Click Login"
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        // Step 4: login(email, password)
        String token = managerLoginController.login(
                request.getEmail(),
                request.getPassword()
        );

        if (token == null) {
            // Step 8a: setErrorMessage("Invalid email or password")
            return ResponseEntity
                    .status(401)
                    .body(Map.of("message", "Invalid email or password"));
        }

        // Step 9: return token → Step 11: redirectToDashboard()
        return ResponseEntity.ok(Map.of("token", token));
    }

    // DTO - holds the incoming request data (email + password)
    @Data
    public static class LoginRequest {
        private String email;
        private String password;
    }
}
