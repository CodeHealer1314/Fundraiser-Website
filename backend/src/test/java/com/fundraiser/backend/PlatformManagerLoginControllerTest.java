package com.fundraiser.backend;

import com.fundraiser.backend.config.JwtUtil;
import com.fundraiser.backend.controller.PlatformManagerLoginController;
import com.fundraiser.backend.entity.PlatformManager;
import com.fundraiser.backend.repository.PlatformManagerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlatformManagerLoginControllerTest {

    @Mock
    private PlatformManagerRepository platformManagerRepository;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private PlatformManagerLoginController platformManagerLoginController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_validCredentials_returnsToken() {
        // Arrange
        String email = "manager@example.com";
        String password = "password123";

        PlatformManager mockManager = new PlatformManager();
        mockManager.setEmail(email);

        when(platformManagerRepository.findByEmail(email))
                .thenReturn(Optional.of(mockManager));
        when(jwtUtil.generateToken(email))
                .thenReturn("mock-jwt-token");

        // Act
        String result = platformManagerLoginController.login(email, password);

        // Assert
        verify(platformManagerRepository, times(1)).findByEmail(email);
    }

    @Test
    void login_invalidCredentials_returnsNull() {
        // Arrange
        String email = "wrong@example.com";
        String password = "wrongpassword";

        when(platformManagerRepository.findByEmail(email))
                .thenReturn(Optional.empty());

        // Act
        String result = platformManagerLoginController.login(email, password);

        // Assert
        assertNull(result);
        verify(platformManagerRepository, times(1)).findByEmail(email);
        verify(jwtUtil, never()).generateToken(anyString());
    }
}