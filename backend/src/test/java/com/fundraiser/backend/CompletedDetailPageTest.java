package com.fundraiser.backend;

import com.fundraiser.backend.boundary.CompletedDetailPage;
import com.fundraiser.backend.controller.ViewCompletedController;
import com.fundraiser.backend.entity.FundRaisingActivity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompletedDetailPageTest {

    @Mock
    private ViewCompletedController viewCompletedController;

    @InjectMocks
    private CompletedDetailPage completedDetailPage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadCompletedActivity_activityExists_returns200() {
        // Arrange
        FundRaisingActivity mockActivity = new FundRaisingActivity();
        mockActivity.setTitle("Help Build a School");
        mockActivity.setStatus("completed");

        when(viewCompletedController.loadCompletedActivity("1"))
                .thenReturn(mockActivity);

        // Act
        ResponseEntity<?> response = completedDetailPage.loadCompletedActivity("1");

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(viewCompletedController, times(1)).loadCompletedActivity("1");
    }

    @Test
    void loadCompletedActivity_activityNotFound_returns404() {
        // Arrange
        when(viewCompletedController.loadCompletedActivity("99"))
                .thenReturn(null);

        // Act
        ResponseEntity<?> response = completedDetailPage.loadCompletedActivity("99");

        // Assert
        assertNotNull(response);
        assertEquals(404, response.getStatusCode().value());
    }
}