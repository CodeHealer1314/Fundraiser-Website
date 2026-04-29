package com.fundraiser.backend;

import com.fundraiser.backend.config.JwtUtil;
import com.fundraiser.backend.controller.ManagerLoginController;
import com.fundraiser.backend.entity.AdminUser;
import com.fundraiser.backend.repository.AdminUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ManagerLoginControllerTest {

    @Mock
    private AdminUserRepository adminUserRepository;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private ManagerLoginController managerLoginController;

    private AdminUser mockAdmin;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockAdmin = new AdminUser();
        mockAdmin.setEmail("admin@test.com");
        mockAdmin.setPassword(new BCryptPasswordEncoder().encode("password123"));
    }

    @Test
    void login_validCredentials_returnsToken() {
        when(adminUserRepository.findByEmail("admin@test.com"))
                .thenReturn(Optional.of(mockAdmin));
        when(jwtUtil.generateToken("admin@test.com"))
                .thenReturn("mock.jwt.token");

        String result = managerLoginController.login("admin@test.com", "password123");

        assertNotNull(result);
        assertEquals("mock.jwt.token", result);
    }

    @Test
    void login_wrongPassword_returnsNull() {
        when(adminUserRepository.findByEmail("admin@test.com"))
                .thenReturn(Optional.of(mockAdmin));

        String result = managerLoginController.login("admin@test.com", "wrongpassword");

        assertNull(result);
    }

    @Test
    void login_userNotFound_returnsNull() {
        when(adminUserRepository.findByEmail("unknown@test.com"))
                .thenReturn(Optional.empty());

        String result = managerLoginController.login("unknown@test.com", "password123");

        assertNull(result);
    }
}