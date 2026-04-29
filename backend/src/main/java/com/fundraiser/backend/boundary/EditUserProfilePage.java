package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.UpdateUserProfileController;
import com.fundraiser.backend.entity.UserProfile;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EditUserProfilePage {

    private final UpdateUserProfileController updateUserProfileController;

    // Empty implementation - will implement in Step 4
    @GetMapping("/profiles/{profileId}")
    public ResponseEntity<?> loadProfile(@PathVariable String profileId) {
        // Step 2: loadProfile(profileId)
        UserProfile profile = updateUserProfileController.loadProfile(profileId);

        if (profile == null) {
            return ResponseEntity
                    .status(404)
                    .body(Map.of("message", "Profile not found"));
        }

        // Step 5: renderProfile(profile)
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/profiles/{profileId}")
    public ResponseEntity<?> updateProfile(
            @PathVariable String profileId,
            @RequestBody ProfileUpdateRequest request) {

        // Convert request to map
        Map<String, String> data = new java.util.HashMap<>();
        data.put("fullname", request.getFullname());
        data.put("username", request.getUsername());
        data.put("email", request.getEmail());
        data.put("role", request.getRole());

        // Step 8: updateProfile(profileId, data)
        boolean result = updateUserProfileController.updateProfile(profileId, data);

        if (!result) {
            // Step 12a: setErrorMessage("Update failed")
            return ResponseEntity
                    .status(400)
                    .body(Map.of("message", "Update failed"));
        }

        // Step 12: setSuccessMessage("Profile updated successfully")
        return ResponseEntity.ok(Map.of("message", "Profile updated successfully"));
    }

    // DTO - holds incoming request data
    @Data
    public static class ProfileUpdateRequest {
        private String fullname;
        private String username;
        private String email;
        private String role;
    }
}
