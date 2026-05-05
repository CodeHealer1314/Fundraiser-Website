package com.fundraiser.backend;

import com.fundraiser.backend.boundary.PlatformManagerDashboardPage;
import com.fundraiser.backend.controller.PlatformManagerLogoutController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlatformManagerDashboardPageTest {

    @Mock
    private PlatformManagerLogoutController platformManagerLogoutController;

    @InjectMocks
    private PlatformManagerDashboardPage platformManagerDashboardPage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void openModal_logoutSuccess_returns200() {
        // Arrange
        when(platformManagerLogoutController.handleLogout())
                .thenReturn(true);

        // Act
        ResponseEntity<?> response = platformManagerDashboardPage.openModal();

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(platformManagerLogoutController, times(1)).handleLogout();
    }

    @Test
    void openModal_logoutFailed_returns400() {
        // Arrange
        when(platformManagerLogoutController.handleLogout())
                .thenReturn(false);

        // Act
        ResponseEntity<?> response = platformManagerDashboardPage.openModal();

        // Assert
        assertNotNull(response);
        assertEquals(400, response.getStatusCode().value());
        verify(platformManagerLogoutController, times(1)).handleLogout();
    }
}