package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.UserProfile;
import com.fundraiser.backend.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViewUserProfileController {

    private final UserProfileRepository userProfileRepository;

    // Retrieves all user profiles from database
    public List<UserProfile> loadProfiles() {
        return userProfileRepository.findAll();
    }
}