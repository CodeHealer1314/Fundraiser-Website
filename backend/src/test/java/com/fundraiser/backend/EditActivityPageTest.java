package com.fundraiser.backend;

import com.fundraiser.backend.boundary.EditActivityPage;
import com.fundraiser.backend.controller.UpdateActivityController;
import com.fundraiser.backend.entity.FundRaisingActivity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EditActivityPageTest {

    @Mock
    private UpdateActivityController updateActivityController;

    @InjectMocks
    private EditActivityPage editActivityPage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadActivity_activityExists_returns200() {
        // Arrange
        FundRaisingActivity mockActivity = new FundRaisingActivity();
        mockActivity.setTitle("Help Build a School");

        when(updateActivityController.loadActivity("1")).thenReturn(mockActivity);

        // Act
        ResponseEntity<?> response = editActivityPage.loadActivity("1");

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void loadActivity_activityNotFound_returns404() {
        // Arrange
        when(updateActivityController.loadActivity("99")).thenReturn(null);

        // Act
        ResponseEntity<?> response = editActivityPage.loadActivity("99");

        // Assert
        assertNotNull(response);
        assertEquals(404, response.getStatusCode().value());
    }

    @Test
    void updateActivity_validData_returns200() {
        // Arrange
        EditActivityPage.ActivityUpdateRequest request = new EditActivityPage.ActivityUpdateRequest();
        request.setTitle("New Title");
        request.setDescription("New Description");
        request.setCategory("Education");
        request.setGoalAmount(5000.0);

        when(updateActivityController.updateActivity(eq("1"), any())).thenReturn(true);

        // Act
        ResponseEntity<?> response = editActivityPage.updateActivity("1", request);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void updateActivity_failed_returns500() {
        // Arrange
        EditActivityPage.ActivityUpdateRequest request = new EditActivityPage.ActivityUpdateRequest();
        request.setTitle("New Title");

        when(updateActivityController.updateActivity(eq("99"), any())).thenReturn(false);

        // Act
        ResponseEntity<?> response = editActivityPage.updateActivity("99", request);

        // Assert
        assertNotNull(response);
        assertEquals(500, response.getStatusCode().value());
    }
}