package com.fundraiser.backend;

import com.fundraiser.backend.boundary.CreateActivityPage;
import com.fundraiser.backend.controller.CreateActivityController;
import com.fundraiser.backend.entity.FundRaisingActivity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateActivityPageTest {

    @Mock
    private CreateActivityController createActivityController;

    @InjectMocks
    private CreateActivityPage createActivityPage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleSubmit_validData_returns200() {
        // Arrange
        CreateActivityPage.ActivityRequest request = new CreateActivityPage.ActivityRequest();
        request.setTitle("Help Build a School");
        request.setDescription("We need funds to build a school");
        request.setCategory("Education");
        request.setGoalAmount(10000.0);
        request.setFundRaiserId("fr001");

        FundRaisingActivity mockActivity = new FundRaisingActivity();
        mockActivity.setTitle("Help Build a School");

        when(createActivityController.validateActivity(any())).thenReturn(true);
        when(createActivityController.createActivity(any())).thenReturn(mockActivity);

        // Act
        ResponseEntity<?> response = createActivityPage.handleSubmit(request);

        // Assert
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void handleSubmit_invalidData_returns400() {
        // Arrange
        CreateActivityPage.ActivityRequest request = new CreateActivityPage.ActivityRequest();
        request.setTitle(null);
        request.setDescription("We need funds");
        request.setCategory("Education");
        request.setGoalAmount(10000.0);
        request.setFundRaiserId("fr001");

        when(createActivityController.validateActivity(any())).thenReturn(false);

        // Act
        ResponseEntity<?> response = createActivityPage.handleSubmit(request);

        // Assert
        assertEquals(400, response.getStatusCode().value());
    }
}