package com.fundraiser.backend;

import com.fundraiser.backend.controller.DoneeViewActivityController;
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

class DoneeViewActivityControllerTest {

    @Mock
    private FundRaisingActivityRepository fundRaisingActivityRepository;

    @InjectMocks
    private DoneeViewActivityController doneeViewActivityController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadActivityDetails_activityFound_returnsActivity() {
        // Arrange
        String activityId = "1";

        FundRaisingActivity activity = new FundRaisingActivity();
        activity.setTitle("Help Build a School");

        when(fundRaisingActivityRepository.findById(1L))
                .thenReturn(Optional.of(activity));

        // Act
        FundRaisingActivity result =
                doneeViewActivityController.loadActivityDetails(activityId);

        // Assert
        assertNotNull(result);
        assertEquals("Help Build a School", result.getTitle());
        verify(fundRaisingActivityRepository, times(1)).findById(1L);
    }

    @Test
    void loadActivityDetails_activityNotFound_returnsNull() {
        // Arrange
        String activityId = "999";

        when(fundRaisingActivityRepository.findById(999L))
                .thenReturn(Optional.empty());

        // Act
        FundRaisingActivity result =
                doneeViewActivityController.loadActivityDetails(activityId);

        // Assert
        assertNull(result);
        verify(fundRaisingActivityRepository, times(1)).findById(999L);
    }
}