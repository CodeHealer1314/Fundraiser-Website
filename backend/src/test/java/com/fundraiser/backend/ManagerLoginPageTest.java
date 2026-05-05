package com.fundraiser.backend;

import com.fundraiser.backend.boundary.ManagerLoginPage;
import com.fundraiser.backend.controller.ManagerLoginController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ManagerLoginPageTest {

    @Mock
    private ManagerLoginController managerLoginController;

    @InjectMocks
    private ManagerLoginPage managerLoginPage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_validCredentials_returns200WithToken() {
        // Arrange
        ManagerLoginPage.LoginRequest request = new ManagerLoginPage.LoginRequest();
        request.setEmail("admin@example.com");
        request.setPassword("password123");

        when(managerLoginController.login("admin@example.com", "password123"))
                .thenReturn("mock-jwt-token");

        // Act
        ResponseEntity<?> response = managerLoginPage.login(request);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(managerLoginController, times(1))
                .login("admin@example.com", "password123");
    }

    @Test
    void login_invalidCredentials_returns401WithErrorMessage() {
        // Arrange
        ManagerLoginPage.LoginRequest request = new ManagerLoginPage.LoginRequest();
        request.setEmail("wrong@example.com");
        request.setPassword("wrongpassword");

        when(managerLoginController.login("wrong@example.com", "wrongpassword"))
                .thenReturn(null);

        // Act
        ResponseEntity<?> response = managerLoginPage.login(request);

        // Assert
        assertNotNull(response);
        assertEquals(401, response.getStatusCode().value());
        verify(managerLoginController, times(1))
                .login("wrong@example.com", "wrongpassword");
    }
}
