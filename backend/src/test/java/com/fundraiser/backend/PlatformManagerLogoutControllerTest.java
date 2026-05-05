package com.fundraiser.backend;

import com.fundraiser.backend.controller.PlatformManagerLogoutController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class PlatformManagerLogoutControllerTest {

    @InjectMocks
    private PlatformManagerLogoutController platformManagerLogoutController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleLogout_validSession_returnsTrue() {
        // Act
        boolean result = platformManagerLogoutController.handleLogout();

        // Assert
        assertTrue(result);
    }

    @Test
    void handleLogout_calledTwice_returnsTrue() {
        // Act
        boolean firstResult = platformManagerLogoutController.handleLogout();
        boolean secondResult = platformManagerLogoutController.handleLogout();

        // Assert
        assertTrue(firstResult);
        assertTrue(secondResult);
    }
}