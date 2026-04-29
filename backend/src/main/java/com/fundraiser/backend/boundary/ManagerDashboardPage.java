package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.LogoutController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ManagerDashboardPage {

    private final LogoutController logoutController;

    // Empty implementation - will implement in Step 4
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        boolean result = logoutController.handleLogout();

        if (!result) {
            return ResponseEntity
                    .status(500)
                    .body(Map.of("message", "Logout failed"));
        }

        // Step 9: hideMainView() / showSuccessView()
        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
    }
}
