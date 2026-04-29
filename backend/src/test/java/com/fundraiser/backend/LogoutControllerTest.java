package com.fundraiser.backend;

import com.fundraiser.backend.controller.LogoutController;
import com.fundraiser.backend.entity.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LogoutControllerTest {

    @Mock
    private Session session;

    @InjectMocks
    private LogoutController logoutController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleLogout_successfulLogout_returnsTrue() {
        // Arrange
        when(session.clearSession()).thenReturn(true);

        // Act
        boolean result = logoutController.handleLogout();

        // Assert
        assertTrue(result);
        verify(session, times(1)).clearSession();
    }

    @Test
    void handleLogout_sessionClearFails_returnsFalse() {
        // Arrange
        when(session.clearSession()).thenReturn(false);

        // Act
        boolean result = logoutController.handleLogout();

        // Assert
        assertFalse(result);
    }
}