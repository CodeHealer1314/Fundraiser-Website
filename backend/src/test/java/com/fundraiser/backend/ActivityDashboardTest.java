package com.fundraiser.backend;

import com.fundraiser.backend.boundary.ActivityDashboard;
import com.fundraiser.backend.controller.ViewActivityController;
import com.fundraiser.backend.entity.FundRaisingActivity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ActivityDashboardTest {

    @Mock
    private ViewActivityController viewActivityController;

    @InjectMocks
    private ActivityDashboard activityDashboard;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadActivities_activitiesExist_returns200() {
        // Arrange
        String fundRaiserId = "fr001";

        FundRaisingActivity activity1 = new FundRaisingActivity();
        activity1.setTitle("Help Build a School");

        FundRaisingActivity activity2 = new FundRaisingActivity();
        activity2.setTitle("Clean Water Project");

        when(viewActivityController.loadActivities(fundRaiserId))
                .thenReturn(Arrays.asList(activity1, activity2));

        // Act
        ResponseEntity<?> response = activityDashboard.loadActivities(fundRaiserId);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(viewActivityController, times(1)).loadActivities(fundRaiserId);
    }

    @Test
    void loadActivities_noActivities_returns200WithEmptyMessage() {
        // Arrange
        String fundRaiserId = "fr002";

        when(viewActivityController.loadActivities(fundRaiserId))
                .thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<?> response = activityDashboard.loadActivities(fundRaiserId);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
    }
}