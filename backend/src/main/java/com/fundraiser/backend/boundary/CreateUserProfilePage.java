package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.CreateUserProfileController;
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
public class CreateUserProfilePage {

    private final CreateUserProfileController createUserProfileController;

    // Empty implementation - will implement in Step 4
    @PostMapping("/profiles")
    public ResponseEntity<?> handleSubmit(@RequestBody ProfileRequest request) {
        // Convert request to map for controller
        Map<String, String> data = new java.util.HashMap<>();
        data.put("fullname", request.getFullname());
        data.put("username", request.getUsername());
        data.put("email", request.getEmail());
        data.put("role", request.getRole());

        // Step 5: validateProfile(data)
        if (!createUserProfileController.validateProfile(data)) {
            return ResponseEntity
                    .status(400)
                    .body(Map.of("message", "Validation failed - please check all fields"));
        }

        // Step 7: createProfile(data)
        UserProfile profile = createUserProfileController.createProfile(data);

        if (profile == null) {
            return ResponseEntity
                    .status(500)
                    .body(Map.of("message", "Failed to create profile"));
        }

        // Step 11: showSuccessSummary(profile)
        return ResponseEntity.ok(Map.of(
                "message", "Profile created successfully",
                "userId", profile.getUserId(),
                "createdAt", profile.getCreatedAt().toString(),
                "fullname", profile.getFullname(),
                "username", profile.getUsername(),
                "email", profile.getEmail(),
                "role", profile.getRole()
        ));
    }

    // DTO - holds incoming request data
    @Data
    public static class ProfileRequest {
        private String fullname;
        private String username;
        private String email;
        private String role;
    }
}
