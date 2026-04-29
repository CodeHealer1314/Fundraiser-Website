package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.UserProfile;
import com.fundraiser.backend.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SuspendUserProfileController {

    private final UserProfileRepository userProfileRepository;

    // Suspends a user profile by ID
    public boolean handleSuspend(String profileId) {
        Optional<UserProfile> profileOpt = userProfileRepository.findById(profileId);

        // Profile not found → return false
        if (profileOpt.isEmpty()) {
            return false;
        }

        UserProfile profile = profileOpt.get();

        // Already suspended → return false
        if (profile.isSuspended()) {
            return false;
        }

        // Suspend the profile
        profile.setSuspended(true);
        userProfileRepository.save(profile);
        return true;
    }
}