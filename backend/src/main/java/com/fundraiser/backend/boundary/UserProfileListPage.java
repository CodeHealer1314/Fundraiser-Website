package com.fundraiser.backend.boundary;

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

    // Empty implementation - will implement in Step 4
    @GetMapping("/profiles")
    public ResponseEntity<?> loadProfiles() {
        List<UserProfile> profiles = viewUserProfileController.loadProfiles();

        // Step 6a: no profiles found
        if (profiles.isEmpty()) {
            return ResponseEntity.ok(Map.of("message", "No user profiles found"));
        }

        // Step 6: renderProfileList(results)
        return ResponseEntity.ok(profiles);
    }
}
