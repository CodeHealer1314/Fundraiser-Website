package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.DoneeLogoutController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/donee")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DoneeLogoutPage {

    private final DoneeLogoutController doneeLogoutController;

    // POST /api/donee/logout
    // BCE boundary entry point — maps to sequence diagram step 1 "Click Logout"
    @PostMapping("/logout")
    public ResponseEntity<?> clickLogout(
            @RequestHeader("Authorization") String authHeader) {

        // Extract token from Bearer header
        String token = authHeader.replace("Bearer ", "");

        // Step 2: handleLogout(token)
        boolean success = doneeLogoutController.handleLogout(token);

        if (!success) {
            // Step 6a: showErrorMessage("Logout failed")
            return ResponseEntity
                    .status(400)
                    .body(Map.of("message", "Logout failed"));
        }

        // Step 7: redirectToHome()
        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
    }
}