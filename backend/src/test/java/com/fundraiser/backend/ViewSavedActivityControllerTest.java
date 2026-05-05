package com.fundraiser.backend;

import com.fundraiser.backend.controller.ViewSavedActivityController;
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

class ViewSavedActivityControllerTest {

    @Mock
    private FundRaisingActivityRepository fundRaisingActivityRepository;

    @InjectMocks
    private ViewSavedActivityController viewSavedActivityController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadSavedActivityDetails_activityFound_returnsActivity() {
        // Arrange
        String doneeId = "donee001";
        String activityId = "1";

        FundRaisingActivity activity = new FundRaisingActivity();
        activity.setTitle("Help Build a School");

        when(fundRaisingActivityRepository.findById(1L))
                .thenReturn(Optional.of(activity));

        // Act
        FundRaisingActivity result =
                viewSavedActivityController.loadSavedActivityDetails(doneeId, activityId);

        // Assert
        assertNotNull(result);
        assertEquals("Help Build a School", result.getTitle());
        verify(fundRaisingActivityRepository, times(1)).findById(1L);
    }

    @Test
    void loadSavedActivityDetails_activityNotFound_returnsNull() {
        // Arrange
        String doneeId = "donee001";
        String activityId = "999";

        when(fundRaisingActivityRepository.findById(999L))
                .thenReturn(Optional.empty());

        // Act
        FundRaisingActivity result =
                viewSavedActivityController.loadSavedActivityDetails(doneeId, activityId);

        // Assert
        assertNull(result);
        verify(fundRaisingActivityRepository, times(1)).findById(999L);
    }
}
