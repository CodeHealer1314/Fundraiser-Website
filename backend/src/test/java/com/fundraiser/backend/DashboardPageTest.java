package com.fundraiser.backend;

import com.fundraiser.backend.boundary.DashboardPage;
import com.fundraiser.backend.controller.LogoutController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DashboardPageTest {

    @Mock
    private LogoutController logoutController;

    @InjectMocks
    private DashboardPage dashboardPage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void logout_successful_returns200() {
        // Arrange
        when(logoutController.handleLogout()).thenReturn(true);

        // Act
        ResponseEntity<?> response = dashboardPage.logout();

        // Assert
        assertEquals(200, response.getStatusCode().value());
        verify(logoutController, times(1)).handleLogout();
    }

    @Test
    void logout_failed_returns500() {
        // Arrange
        when(logoutController.handleLogout()).thenReturn(false);

        // Act
        ResponseEntity<?> response = dashboardPage.logout();

        // Assert
        assertEquals(500, response.getStatusCode().value());
    }
}
