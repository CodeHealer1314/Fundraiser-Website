package com.fundraiser.backend;

import com.fundraiser.backend.controller.SuspendUserProfileController;
import com.fundraiser.backend.entity.UserProfile;
import com.fundraiser.backend.repository.UserProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SuspendUserProfileControllerTest {

    @Mock
    private UserProfileRepository userProfileRepository;

    @InjectMocks
    private SuspendUserProfileController suspendUserProfileController;

    private UserProfile mockProfile;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockProfile = new UserProfile();
        mockProfile.setUserId("user-001");
        mockProfile.setFullname("John Doe");
        mockProfile.setUsername("johndoe");
        mockProfile.setEmail("john@test.com");
        mockProfile.setRole("FUND_RAISER");
        mockProfile.setSuspended(false);
    }

    @Test
    void handleSuspend_profileExists_returnsTrue() {
        // Arrange
        when(userProfileRepository.findById("user-001"))
                .thenReturn(Optional.of(mockProfile));
        when(userProfileRepository.save(any(UserProfile.class)))
                .thenReturn(mockProfile);

        // Act
        boolean result = suspendUserProfileController.handleSuspend("user-001");

        // Assert
        assertTrue(result);
        assertTrue(mockProfile.isSuspended());
        verify(userProfileRepository, times(1)).save(mockProfile);
    }

    @Test
    void handleSuspend_profileNotFound_returnsFalse() {
        // Arrange
        when(userProfileRepository.findById("unknown-id"))
                .thenReturn(Optional.empty());

        // Act
        boolean result = suspendUserProfileController.handleSuspend("unknown-id");

        // Assert
        assertFalse(result);
    }

    @Test
    void handleSuspend_alreadySuspended_returnsFalse() {
        // Arrange
        mockProfile.setSuspended(true);
        when(userProfileRepository.findById("user-001"))
                .thenReturn(Optional.of(mockProfile));

        // Act
        boolean result = suspendUserProfileController.handleSuspend("user-001");

        // Assert
        assertFalse(result);
    }
}