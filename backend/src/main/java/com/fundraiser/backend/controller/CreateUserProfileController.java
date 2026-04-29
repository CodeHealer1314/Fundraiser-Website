package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.UserProfile;
import com.fundraiser.backend.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CreateUserProfileController {

    private final UserProfileRepository userProfileRepository;

    // Validates that all required fields are present
    public boolean validateProfile(Object data) {
        if (!(data instanceof Map)) return false;

        Map<?, ?> map = (Map<?, ?>) data;
        String fullname = (String) map.get("fullname");
        String username = (String) map.get("username");
        String email = (String) map.get("email");
        String role = (String) map.get("role");

        return fullname != null && !fullname.isEmpty() &&
                username != null && !username.isEmpty() &&
                email != null && !email.isEmpty() &&
                role != null && !role.isEmpty();
    }

    // Creates and saves the user profile
    public UserProfile createProfile(Object data) {
        // Validate first
        if (!validateProfile(data)) {
            return null;
        }

        Map<?, ?> map = (Map<?, ?>) data;

        // Create new profile
        UserProfile profile = new UserProfile();

        // Auto generate ID and timestamp
        profile.refreshAuto();

        // Set profile details
        profile.setFullname((String) map.get("fullname"));
        profile.setUsername((String) map.get("username"));
        profile.setEmail((String) map.get("email"));
        profile.setRole((String) map.get("role"));

        // Save to database
        return userProfileRepository.save(profile);
    }
}
