package com.fundraiser.backend;

import com.fundraiser.backend.controller.CreateUserProfileController;
import com.fundraiser.backend.entity.UserProfile;
import com.fundraiser.backend.repository.UserProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateUserProfileControllerTest {

    @Mock
    private UserProfileRepository userProfileRepository;

    @InjectMocks
    private CreateUserProfileController createUserProfileController;

    private Map<String, String> validData;
    private Map<String, String> invalidData;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Valid profile data
        validData = new HashMap<>();
        validData.put("fullname", "John Doe");
        validData.put("username", "johndoe");
        validData.put("email", "john@test.com");
        validData.put("role", "FUND_RAISER");

        // Invalid profile data - missing required fields
        invalidData = new HashMap<>();
        invalidData.put("fullname", "");
        invalidData.put("username", "");
        invalidData.put("email", "");
        invalidData.put("role", "");
    }

    @Test
    void validateProfile_validData_returnsTrue() {
        // Act
        boolean result = createUserProfileController.validateProfile(validData);

        // Assert
        assertTrue(result);
    }

    @Test
    void validateProfile_invalidData_returnsFalse() {
        // Act
        boolean result = createUserProfileController.validateProfile(invalidData);

        // Assert
        assertFalse(result);
    }

    @Test
    void createProfile_validData_returnsUserProfile() {
        // Arrange
        UserProfile mockProfile = new UserProfile();
        mockProfile.setFullname("John Doe");
        mockProfile.setUsername("johndoe");
        mockProfile.setEmail("john@test.com");
        mockProfile.setRole("FUND_RAISER");
        when(userProfileRepository.save(any(UserProfile.class)))
                .thenReturn(mockProfile);

        // Act
        UserProfile result = createUserProfileController.createProfile(validData);

        // Assert
        assertNotNull(result);
        assertEquals("John Doe", result.getFullname());
        assertEquals("johndoe", result.getUsername());
        assertEquals("john@test.com", result.getEmail());
        assertEquals("FUND_RAISER", result.getRole());
    }

    @Test
    void createProfile_invalidData_returnsNull() {
        // Act
        UserProfile result = createUserProfileController.createProfile(invalidData);

        // Assert
        assertNull(result);
    }
}
