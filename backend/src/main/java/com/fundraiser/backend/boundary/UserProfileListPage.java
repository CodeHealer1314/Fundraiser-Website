package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.SuspendUserProfileController;
import com.fundraiser.backend.controller.ViewUserProfileController;
import com.fundraiser.backend.entity.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserProfileListPage {

    private final ViewUserProfileController viewUserProfileController;
    private final SuspendUserProfileController suspendUserProfileController;

    @GetMapping("/profiles")
    public ResponseEntity<?> loadProfiles() {
        List<UserProfile> profiles = viewUserProfileController.loadProfiles();

        if (profiles.isEmpty()) {
            return ResponseEntity.ok(Map.of("message", "No user profiles found"));
        }

        return ResponseEntity.ok(profiles);
    }


    @PatchMapping("/profiles/{profileId}/suspend")
    public ResponseEntity<?> suspendProfile(@PathVariable String profileId) {
        // Step 4: handleSuspend(profileId)
        boolean result = suspendUserProfileController.handleSuspend(profileId);

        if (!result) {
            return ResponseEntity
                    .status(400)
                    .body(Map.of("message", "Suspension failed - profile not found or already suspended"));
        }

        // Step 8: setSuccessMessage("Profile suspended successfully")
        return ResponseEntity.ok(Map.of("message", "Profile suspended successfully"));
    }
}

