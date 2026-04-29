package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.UserProfile;
import com.fundraiser.backend.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateUserProfileController {

    private final UserProfileRepository userProfileRepository;

    // Loads a single profile by ID
    public UserProfile loadProfile(String profileId) {
        Optional<UserProfile> profileOpt = userProfileRepository.findById(profileId);
        return profileOpt.orElse(null);
    }

    // Updates an existing profile
    public boolean updateProfile(String profileId, Object data) {
        if (!(data instanceof Map)) return false;

        Map<?, ?> map = (Map<?, ?>) data;
        String fullname = (String) map.get("fullname");
        String username = (String) map.get("username");
        String email = (String) map.get("email");
        String role = (String) map.get("role");

        // Validate data
        if (fullname == null || fullname.isEmpty() ||
                username == null || username.isEmpty() ||
                email == null || email.isEmpty() ||
                role == null || role.isEmpty()) {
            return false;
        }

        // Find existing profile
        Optional<UserProfile> profileOpt = userProfileRepository.findById(profileId);
        if (profileOpt.isEmpty()) {
            return false;
        }

        // Update profile fields
        UserProfile profile = profileOpt.get();
        profile.setFullname(fullname);
        profile.setUsername(username);
        profile.setEmail(email);
        profile.setRole(role);

        // Save updated profile
        userProfileRepository.save(profile);
        return true;
    }
}