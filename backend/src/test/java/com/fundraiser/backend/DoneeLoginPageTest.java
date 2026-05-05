package com.fundraiser.backend;

import com.fundraiser.backend.boundary.DoneeLoginPage;
import com.fundraiser.backend.controller.DoneeLoginController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DoneeLoginPageTest {

    @Mock
    private DoneeLoginController doneeLoginController;

    @InjectMocks
    private DoneeLoginPage doneeLoginPage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_validCredentials_returns200WithToken() {
        // Arrange
        DoneeLoginPage.LoginRequest request = new DoneeLoginPage.LoginRequest();
        request.setEmail("donee@example.com");
        request.setPassword("password123");

        when(doneeLoginController.login("donee@example.com", "password123"))
                .thenReturn("mock-jwt-token");

        // Act
        ResponseEntity<?> response = doneeLoginPage.login(request);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(doneeLoginController, times(1))
                .login("donee@example.com", "password123");
    }

    @Test
    void login_invalidCredentials_returns401WithErrorMessage() {
        // Arrange
        DoneeLoginPage.LoginRequest request = new DoneeLoginPage.LoginRequest();
        request.setEmail("wrong@example.com");
        request.setPassword("wrongpassword");

        when(doneeLoginController.login("wrong@example.com", "wrongpassword"))
                .thenReturn(null);

        // Act
        ResponseEntity<?> response = doneeLoginPage.login(request);

        // Assert
        assertNotNull(response);
        assertEquals(401, response.getStatusCode().value());
        verify(doneeLoginController, times(1))
                .login("wrong@example.com", "wrongpassword");
    }
}