package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.LogoutController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/fundraiser")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DashboardPage {

    private final LogoutController logoutController;

    // Step 4: handleLogout()
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {

        // Step 4: handleLogout()
        boolean result = logoutController.handleLogout();

        if (!result) {
            // Logout failed
            return ResponseEntity
                    .status(500)
                    .body(Map.of("message", "Logout failed"));
        }

        // Step 8: closeModal() / redirectToLogin()
        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
    }
}