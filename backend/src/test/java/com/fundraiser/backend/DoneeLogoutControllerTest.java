package com.fundraiser.backend;

import com.fundraiser.backend.controller.DoneeLogoutController;
import com.fundraiser.backend.entity.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class DoneeLogoutControllerTest {

    @InjectMocks
    private DoneeLogoutController doneeLogoutController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleLogout_validToken_returnsTrue() {
        // Arrange
        String token = "mock-jwt-token";

        // Act
        boolean result = doneeLogoutController.handleLogout(token);

        // Assert
        assertTrue(result);
    }

    @Test
    void handleLogout_emptyToken_returnsFalse() {
        // Arrange
        String token = "";

        // Act
        boolean result = doneeLogoutController.handleLogout(token);

        // Assert
        assertFalse(result);
    }
}