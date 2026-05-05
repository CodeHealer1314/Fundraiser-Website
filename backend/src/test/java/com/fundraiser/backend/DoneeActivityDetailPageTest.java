package com.fundraiser.backend;

import com.fundraiser.backend.boundary.DoneeActivityDetailPage;
import com.fundraiser.backend.controller.DoneeViewActivityController;
import com.fundraiser.backend.entity.FundRaisingActivity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DoneeActivityDetailPageTest {

    @Mock
    private DoneeViewActivityController doneeViewActivityController;

    @InjectMocks
    private DoneeActivityDetailPage doneeActivityDetailPage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void clickActivity_activityFound_returns200() {
        // Arrange
        String activityId = "act001";

        FundRaisingActivity activity = new FundRaisingActivity();
        activity.setTitle("Help Build a School");

        when(doneeViewActivityController.loadActivityDetails(activityId))
                .thenReturn(activity);

        // Act
        ResponseEntity<?> response = doneeActivityDetailPage.clickActivity(activityId);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(doneeViewActivityController, times(1))
                .loadActivityDetails(activityId);
    }

    @Test
    void clickActivity_activityNotFound_returns404() {
        // Arrange
        String activityId = "invalid001";

        when(doneeViewActivityController.loadActivityDetails(activityId))
                .thenReturn(null);

        // Act
        ResponseEntity<?> response = doneeActivityDetailPage.clickActivity(activityId);

        // Assert
        assertNotNull(response);
        assertEquals(404, response.getStatusCode().value());
        verify(doneeViewActivityController, times(1))
                .loadActivityDetails(activityId);
    }
}