package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.PlatformManagerLogoutController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PlatformManagerDashboardPage {

    private final PlatformManagerLogoutController platformManagerLogoutController;

    // POST /api/manager/logout
    // BCE boundary entry point — maps to sequence diagram step 1 "Click Log out"
    @PostMapping("/logout")
    public ResponseEntity<?> openModal() {

        // Step 4: handleLogout()
        boolean success = platformManagerLogoutController.handleLogout();

        if (!success) {
            // Alt: logout failed
            return ResponseEntity
                    .status(400)
                    .body(Map.of("message", "Logout failed"));
        }

        // Step 9: redirectToLogin()
        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
    }
}