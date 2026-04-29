package com.fundraiser.backend;

import com.fundraiser.backend.boundary.ActivityListPage;
import com.fundraiser.backend.controller.SuspendActivityController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ActivityListPageTest {

    @Mock
    private SuspendActivityController suspendActivityController;

    @InjectMocks
    private ActivityListPage activityListPage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleSuspend_successful_returns200() {
        // Arrange
        when(suspendActivityController.handleSuspend("1")).thenReturn(true);

        // Act
        ResponseEntity<?> response = activityListPage.handleSuspend("1");

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(suspendActivityController, times(1)).handleSuspend("1");
    }

    @Test
    void handleSuspend_activityNotFound_returns404() {
        // Arrange
        when(suspendActivityController.handleSuspend("99")).thenReturn(false);

        // Act
        ResponseEntity<?> response = activityListPage.handleSuspend("99");

        // Assert
        assertNotNull(response);
        assertEquals(404, response.getStatusCode().value());
    }
}