package com.fundraiser.backend;

import com.fundraiser.backend.boundary.PlatformManagerLoginPage;
import com.fundraiser.backend.controller.PlatformManagerLoginController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlatformManagerLoginPageTest {

    @Mock
    private PlatformManagerLoginController platformManagerLoginController;

    @InjectMocks
    private PlatformManagerLoginPage platformManagerLoginPage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_validCredentials_returns200WithToken() {
        // Arrange
        PlatformManagerLoginPage.LoginRequest request =
                new PlatformManagerLoginPage.LoginRequest();
        request.setEmail("manager@example.com");
        request.setPassword("password123");

        when(platformManagerLoginController.login(
                "manager@example.com", "password123"))
                .thenReturn("mock-jwt-token");

        // Act
        ResponseEntity<?> response = platformManagerLoginPage.login(request);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(platformManagerLoginController, times(1))
                .login("manager@example.com", "password123");
    }

    @Test
    void login_invalidCredentials_returns401WithErrorMessage() {
        // Arrange
        PlatformManagerLoginPage.LoginRequest request =
                new PlatformManagerLoginPage.LoginRequest();
        request.setEmail("wrong@example.com");
        request.setPassword("wrongpassword");

        when(platformManagerLoginController.login(
                "wrong@example.com", "wrongpassword"))
                .thenReturn(null);

        // Act
        ResponseEntity<?> response = platformManagerLoginPage.login(request);

        // Assert
        assertNotNull(response);
        assertEquals(401, response.getStatusCode().value());
        verify(platformManagerLoginController, times(1))
                .login("wrong@example.com", "wrongpassword");
    }
}