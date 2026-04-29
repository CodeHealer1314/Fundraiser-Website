package com.fundraiser.backend;

import com.fundraiser.backend.controller.ViewCompletedController;
import com.fundraiser.backend.entity.FundRaisingActivity;
import com.fundraiser.backend.repository.FundRaisingActivityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ViewCompletedControllerTest {

    @Mock
    private FundRaisingActivityRepository fundRaisingActivityRepository;

    @InjectMocks
    private ViewCompletedController viewCompletedController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadCompletedActivity_activityExists_returnsActivity() {
        // Arrange
        FundRaisingActivity activity = new FundRaisingActivity();
        activity.setTitle("Help Build a School");
        activity.setStatus("completed");

        when(fundRaisingActivityRepository.findById(1L))
                .thenReturn(Optional.of(activity));

        // Act
        FundRaisingActivity result = viewCompletedController.loadCompletedActivity("1");

        // Assert
        assertNotNull(result);
        assertEquals("Help Build a School", result.getTitle());
        assertEquals("completed", result.getStatus());
    }

    @Test
    void loadCompletedActivity_activityNotFound_returnsNull() {
        // Arrange
        when(fundRaisingActivityRepository.findById(99L))
                .thenReturn(Optional.empty());

        // Act
        FundRaisingActivity result = viewCompletedController.loadCompletedActivity("99");

        // Assert
        assertNull(result);
    }

    @Test
    void loadCompletedActivity_activityNotCompleted_returnsNull() {
        // Arrange
        FundRaisingActivity activity = new FundRaisingActivity();
        activity.setTitle("Active Campaign");
        activity.setStatus("active");

        when(fundRaisingActivityRepository.findById(2L))
                .thenReturn(Optional.of(activity));

        // Act
        FundRaisingActivity result = viewCompletedController.loadCompletedActivity("2");

        // Assert
        assertNull(result);
    }
}