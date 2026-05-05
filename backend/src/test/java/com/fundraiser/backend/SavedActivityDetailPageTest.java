package com.fundraiser.backend;

import com.fundraiser.backend.boundary.SavedActivityDetailPage;
import com.fundraiser.backend.controller.ViewSavedActivityController;
import com.fundraiser.backend.entity.FundRaisingActivity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SavedActivityDetailPageTest {

    @Mock
    private ViewSavedActivityController viewSavedActivityController;

    @InjectMocks
    private SavedActivityDetailPage savedActivityDetailPage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void clickSavedActivity_activityFound_returns200() {
        // Arrange
        String doneeId = "donee001";
        String activityId = "1";

        FundRaisingActivity activity = new FundRaisingActivity();
        activity.setTitle("Help Build a School");

        when(viewSavedActivityController.loadSavedActivityDetails(doneeId, activityId))
                .thenReturn(activity);

        // Act
        ResponseEntity<?> response =
                savedActivityDetailPage.clickSavedActivity(doneeId, activityId);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(viewSavedActivityController, times(1))
                .loadSavedActivityDetails(doneeId, activityId);
    }

    @Test
    void clickSavedActivity_activityNotFound_returns404() {
        // Arrange
        String doneeId = "donee001";
        String activityId = "999";

        when(viewSavedActivityController.loadSavedActivityDetails(doneeId, activityId))
                .thenReturn(null);

        // Act
        ResponseEntity<?> response =
                savedActivityDetailPage.clickSavedActivity(doneeId, activityId);

        // Assert
        assertNotNull(response);
        assertEquals(404, response.getStatusCode().value());
        verify(viewSavedActivityController, times(1))
                .loadSavedActivityDetails(doneeId, activityId);
    }
}