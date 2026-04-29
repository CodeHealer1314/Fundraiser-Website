package com.fundraiser.backend;

import com.fundraiser.backend.controller.UpdateActivityController;
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

class UpdateActivityControllerTest {

    @Mock
    private FundRaisingActivityRepository fundRaisingActivityRepository;

    @InjectMocks
    private UpdateActivityController updateActivityController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadActivity_activityExists_returnsActivity() {
        // Arrange
        FundRaisingActivity activity = new FundRaisingActivity();
        activity.setTitle("Help Build a School");

        when(fundRaisingActivityRepository.findById(1L))
                .thenReturn(Optional.of(activity));

        // Act
        FundRaisingActivity result = updateActivityController.loadActivity("1");

        // Assert
        assertNotNull(result);
        assertEquals("Help Build a School", result.getTitle());
    }

    @Test
    void loadActivity_activityNotFound_returnsNull() {
        // Arrange
        when(fundRaisingActivityRepository.findById(99L))
                .thenReturn(Optional.empty());

        // Act
        FundRaisingActivity result = updateActivityController.loadActivity("99");

        // Assert
        assertNull(result);
    }

    @Test
    void updateActivity_validData_returnsTrue() {
        // Arrange
        FundRaisingActivity existing = new FundRaisingActivity();
        existing.setTitle("Old Title");

        FundRaisingActivity updated = new FundRaisingActivity();
        updated.setTitle("New Title");
        updated.setDescription("New Description");
        updated.setCategory("Education");
        updated.setGoalAmount(5000.0);

        when(fundRaisingActivityRepository.findById(1L))
                .thenReturn(Optional.of(existing));
        when(fundRaisingActivityRepository.save(any(FundRaisingActivity.class)))
                .thenReturn(existing);

        // Act
        boolean result = updateActivityController.updateActivity("1", updated);

        // Assert
        assertTrue(result);
    }

    @Test
    void updateActivity_activityNotFound_returnsFalse() {
        // Arrange
        when(fundRaisingActivityRepository.findById(99L))
                .thenReturn(Optional.empty());

        FundRaisingActivity updated = new FundRaisingActivity();
        updated.setTitle("New Title");

        // Act
        boolean result = updateActivityController.updateActivity("99", updated);

        // Assert
        assertFalse(result);
    }
}