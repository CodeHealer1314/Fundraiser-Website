package com.fundraiser.backend;

import com.fundraiser.backend.controller.UpdateUserProfileController;
import com.fundraiser.backend.entity.UserProfile;
import com.fundraiser.backend.repository.UserProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateUserProfileControllerTest {

    @Mock
    private UserProfileRepository userProfileRepository;

    @InjectMocks
    private UpdateUserProfileController updateUserProfileController;

    private UserProfile mockProfile;
    private Map<String, String> validData;
    private Map<String, String> invalidData;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create mock profile
        mockProfile = new UserProfile();
        mockProfile.setUserId("user-001");
        mockProfile.setFullname("John Doe");
        mockProfile.setUsername("johndoe");
        mockProfile.setEmail("john@test.com");
        mockProfile.setRole("FUND_RAISER");

        // Valid update data
        validData = new HashMap<>();
        validData.put("fullname", "John Updated");
        validData.put("username", "johnupdated");
        validData.put("email", "johnupdated@test.com");
        validData.put("role", "DONEE");

        // Invalid update data
        invalidData = new HashMap<>();
        invalidData.put("fullname", "");
        invalidData.put("username", "");
        invalidData.put("email", "");
        invalidData.put("role", "");
    }

    @Test
    void loadProfile_profileExists_returnsProfile() {
        // Arrange
        when(userProfileRepository.findById("user-001"))
                .thenReturn(Optional.of(mockProfile));

        // Act
        UserProfile result = updateUserProfileController.loadProfile("user-001");

        // Assert
        assertNotNull(result);
        assertEquals("user-001", result.getUserId());
        assertEquals("John Doe", result.getFullname());
    }

    @Test
    void loadProfile_profileNotFound_returnsNull() {
        // Arrange
        when(userProfileRepository.findById("unknown-id"))
                .thenReturn(Optional.empty());

        // Act
        UserProfile result = updateUserProfileController.loadProfile("unknown-id");

        // Assert
        assertNull(result);
    }

    @Test
    void updateProfile_validData_returnsTrue() {
        // Arrange
        when(userProfileRepository.findById("user-001"))
                .thenReturn(Optional.of(mockProfile));
        when(userProfileRepository.save(any(UserProfile.class)))
                .thenReturn(mockProfile);

        // Act
        boolean result = updateUserProfileController.updateProfile("user-001", validData);

        // Assert
        assertTrue(result);
        verify(userProfileRepository, times(1)).save(any(UserProfile.class));
    }

    @Test
    void updateProfile_profileNotFound_returnsFalse() {
        // Arrange
        when(userProfileRepository.findById("unknown-id"))
                .thenReturn(Optional.empty());

        // Act
        boolean result = updateUserProfileController.updateProfile("unknown-id", validData);

        // Assert
        assertFalse(result);
    }

    @Test
    void updateProfile_invalidData_returnsFalse() {
        // Arrange
        when(userProfileRepository.findById("user-001"))
                .thenReturn(Optional.of(mockProfile));

        // Act
        boolean result = updateUserProfileController.updateProfile("user-001", invalidData);

        // Assert
        assertFalse(result);
    }
}
