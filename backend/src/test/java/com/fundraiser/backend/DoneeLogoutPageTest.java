package com.fundraiser.backend;

import com.fundraiser.backend.boundary.DoneeLogoutPage;
import com.fundraiser.backend.controller.DoneeLogoutController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DoneeLogoutPageTest {

    @Mock
    private DoneeLogoutController doneeLogoutController;

    @InjectMocks
    private DoneeLogoutPage doneeLogoutPage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void clickLogout_validToken_returns200() {
        // Arrange
        String authHeader = "Bearer mock-jwt-token";

        when(doneeLogoutController.handleLogout("mock-jwt-token"))
                .thenReturn(true);

        // Act
        ResponseEntity<?> response = doneeLogoutPage.clickLogout(authHeader);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(doneeLogoutController, times(1))
                .handleLogout("mock-jwt-token");
    }

    @Test
    void clickLogout_invalidToken_returns400() {
        // Arrange
        String authHeader = "Bearer invalid-token";

        when(doneeLogoutController.handleLogout("invalid-token"))
                .thenReturn(false);

        // Act
        ResponseEntity<?> response = doneeLogoutPage.clickLogout(authHeader);

        // Assert
        assertNotNull(response);
        assertEquals(400, response.getStatusCode().value());
        verify(doneeLogoutController, times(1))
                .handleLogout("invalid-token");
    }
}