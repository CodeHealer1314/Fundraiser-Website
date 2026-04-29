package com.fundraiser.backend;

import com.fundraiser.backend.controller.ViewUserProfileController;
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

class ViewUserProfileControllerTest {

    @Mock
    private UserProfileRepository userProfileRepository;

    @InjectMocks
    private ViewUserProfileController viewUserProfileController;

    private List<UserProfile> mockProfiles;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create mock profiles
        mockProfiles = new ArrayList<>();

        UserProfile profile1 = new UserProfile();
        profile1.setUserId("user-001");
        profile1.setFullname("John Doe");
        profile1.setUsername("johndoe");
        profile1.setEmail("john@test.com");
        profile1.setRole("FUND_RAISER");

        UserProfile profile2 = new UserProfile();
        profile2.setUserId("user-002");
        profile2.setFullname("Jane Smith");
        profile2.setUsername("janesmith");
        profile2.setEmail("jane@test.com");
        profile2.setRole("DONEE");

        mockProfiles.add(profile1);
        mockProfiles.add(profile2);
    }

    @Test
    void loadProfiles_profilesExist_returnsList() {
        // Arrange
        when(userProfileRepository.findAll())
                .thenReturn(mockProfiles);

        // Act
        List<UserProfile> result = viewUserProfileController.loadProfiles();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getFullname());
        assertEquals("Jane Smith", result.get(1).getFullname());
    }

    @Test
    void loadProfiles_noProfiles_returnsEmptyList() {
        // Arrange
        when(userProfileRepository.findAll())
                .thenReturn(new ArrayList<>());

        // Act
        List<UserProfile> result = viewUserProfileController.loadProfiles();

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }
}
