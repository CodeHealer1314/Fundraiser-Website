package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.UserProfile;
import com.fundraiser.backend.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchUserProfileController {

    private final UserProfileRepository userProfileRepository;

    // Searches profiles by keyword
    public List<UserProfile> searchProfiles(String keyword) {
        // Empty keyword → return empty list
        if (keyword == null || keyword.isEmpty()) {
            return new ArrayList<>();
        }

        return userProfileRepository.searchProfiles(keyword);
    }
}