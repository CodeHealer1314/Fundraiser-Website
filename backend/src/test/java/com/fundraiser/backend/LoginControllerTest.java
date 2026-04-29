package com.fundraiser.backend;

import com.fundraiser.backend.config.JwtUtil;
import com.fundraiser.backend.controller.LoginController;
import com.fundraiser.backend.entity.FundRaiser;
import com.fundraiser.backend.repository.FundRaiserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginControllerTest {

    @Mock
    private FundRaiserRepository fundRaiserRepository;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_validCredentials_returnsToken() {
        // Arrange
        String email = "fundraiser@test.com";
        String rawPassword = "password123";
        String hashedPassword = new BCryptPasswordEncoder().encode(rawPassword);

        FundRaiser mockUser = new FundRaiser();
        mockUser.setEmail(email);
        mockUser.setPassword(hashedPassword);

        when(fundRaiserRepository.findByEmail(email)).thenReturn(Optional.of(mockUser));
        when(jwtUtil.generateToken(email)).thenReturn("mocked-jwt-token");

        // Act
        String result = loginController.login(email, rawPassword);

        // Assert
        assertNotNull(result);
        assertEquals("mocked-jwt-token", result);
    }

    @Test
    void login_invalidEmail_returnsNull() {
        // Arrange
        when(fundRaiserRepository.findByEmail("notfound@test.com"))
                .thenReturn(Optional.empty());

        // Act
        String result = loginController.login("notfound@test.com", "password123");

        // Assert
        assertNull(result);
        verify(jwtUtil, never()).generateToken(any());
    }

    @Test
    void login_wrongPassword_returnsNull() {
        // Arrange
        String email = "fundraiser@test.com";
        String hashedPassword = new BCryptPasswordEncoder().encode("correctpassword");

        FundRaiser mockUser = new FundRaiser();
        mockUser.setEmail(email);
        mockUser.setPassword(hashedPassword);

        when(fundRaiserRepository.findByEmail(email)).thenReturn(Optional.of(mockUser));

        // Act
        String result = loginController.login(email, "wrongpassword");

        // Assert
        assertNull(result);
        verify(jwtUtil, never()).generateToken(any());
    }
}
