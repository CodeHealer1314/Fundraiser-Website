package com.fundraiser.backend;

import com.fundraiser.backend.controller.SuspendActivityController;
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

class SuspendActivityControllerTest {

    @Mock
    private FundRaisingActivityRepository fundRaisingActivityRepository;

    @InjectMocks
    private SuspendActivityController suspendActivityController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleSuspend_activityExists_returnsTrue() {
        // Arrange
        FundRaisingActivity activity = new FundRaisingActivity();
        activity.setStatus("active");

        when(fundRaisingActivityRepository.findById(1L))
                .thenReturn(Optional.of(activity));
        when(fundRaisingActivityRepository.save(any(FundRaisingActivity.class)))
                .thenReturn(activity);

        // Act
        boolean result = suspendActivityController.handleSuspend("1");

        // Assert
        assertTrue(result);
        assertEquals("suspended", activity.getStatus());
        verify(fundRaisingActivityRepository, times(1)).save(activity);
    }

    @Test
    void handleSuspend_activityNotFound_returnsFalse() {
        // Arrange
        when(fundRaisingActivityRepository.findById(99L))
                .thenReturn(Optional.empty());

        // Act
        boolean result = suspendActivityController.handleSuspend("99");

        // Assert
        assertFalse(result);
        verify(fundRaisingActivityRepository, never()).save(any());
    }
}