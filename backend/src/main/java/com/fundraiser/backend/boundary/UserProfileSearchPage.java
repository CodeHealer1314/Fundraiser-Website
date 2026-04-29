package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.SearchUserProfileController;
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
public class UserProfileSearchPage {

    private final SearchUserProfileController searchUserProfileController;

    // Empty implementation - will implement in Step 4
    @GetMapping("/profiles/search")
    public ResponseEntity<?> handleSearch(@RequestParam String keyword) {
        // Step 3: searchProfiles(keyword)
        List<UserProfile> results = searchUserProfileController.searchProfiles(keyword);

        // Step 7a: no profiles found
        if (results.isEmpty()) {
            return ResponseEntity.ok(Map.of("message", "No user profiles found"));
        }

        // Step 7: renderProfileList(results)
        return ResponseEntity.ok(results);
    }
}