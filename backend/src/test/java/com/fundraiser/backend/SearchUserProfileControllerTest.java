package com.fundraiser.backend;

import com.fundraiser.backend.controller.SearchUserProfileController;
import com.fundraiser.backend.entity.UserProfile;
import com.fundraiser.backend.repository.UserProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchUserProfileControllerTest {

    @Mock
    private UserProfileRepository userProfileRepository;

    @InjectMocks
    private SearchUserProfileController searchUserProfileController;

    private List<UserProfile> mockProfiles;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockProfiles = new ArrayList<>();

        UserProfile profile1 = new UserProfile();
        profile1.setUserId("user-001");
        profile1.setFullname("John Doe");
        profile1.setUsername("johndoe");
        profile1.setEmail("john@test.com");
        profile1.setRole("FUND_RAISER");

        UserProfile profile2 = new UserProfile();
        profile2.setUserId("user-002");
        profile2.setFullname("John Smith");
        profile2.setUsername("johnsmith");
        profile2.setEmail("johnsmith@test.com");
        profile2.setRole("DONEE");

        mockProfiles.add(profile1);
        mockProfiles.add(profile2);
    }

    @Test
    void searchProfiles_keywordMatches_returnsList() {
        // Arrange
        when(userProfileRepository.searchProfiles("john"))
                .thenReturn(mockProfiles);

        // Act
        List<UserProfile> result = searchUserProfileController.searchProfiles("john");

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getFullname());
        assertEquals("John Smith", result.get(1).getFullname());
    }

    @Test
    void searchProfiles_noMatches_returnsEmptyList() {
        // Arrange
        when(userProfileRepository.searchProfiles("unknown"))
                .thenReturn(new ArrayList<>());

        // Act
        List<UserProfile> result = searchUserProfileController.searchProfiles("unknown");

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void searchProfiles_emptyKeyword_returnsEmptyList() {
        // Arrange
        when(userProfileRepository.searchProfiles(""))
                .thenReturn(new ArrayList<>());

        // Act
        List<UserProfile> result = searchUserProfileController.searchProfiles("");

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }
}