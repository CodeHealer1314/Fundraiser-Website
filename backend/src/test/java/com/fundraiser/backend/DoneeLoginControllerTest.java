package com.fundraiser.backend;

import com.fundraiser.backend.config.JwtUtil;
import com.fundraiser.backend.controller.DoneeLoginController;
import com.fundraiser.backend.entity.Donee;
import com.fundraiser.backend.repository.DoneeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DoneeLoginControllerTest {

    @Mock
    private DoneeRepository doneeRepository;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private DoneeLoginController doneeLoginController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_validCredentials_returnsToken() {
        // Arrange
        String email = "donee@example.com";
        String password = "password123";

        Donee mockDonee = new Donee();
        mockDonee.setEmail(email);

        when(doneeRepository.findByEmail(email))
                .thenReturn(Optional.of(mockDonee));
        when(jwtUtil.generateToken(email))
                .thenReturn("mock-jwt-token");

        // Act
        String result = doneeLoginController.login(email, password);

        // Assert
        verify(doneeRepository, times(1)).findByEmail(email);
    }

    @Test
    void login_invalidCredentials_returnsNull() {
        // Arrange
        String email = "wrong@example.com";
        String password = "wrongpassword";

        when(doneeRepository.findByEmail(email))
                .thenReturn(Optional.empty());

        // Act
        String result = doneeLoginController.login(email, password);

        // Assert
        assertNull(result);
        verify(doneeRepository, times(1)).findByEmail(email);
        verify(jwtUtil, never()).generateToken(anyString());
    }
}
