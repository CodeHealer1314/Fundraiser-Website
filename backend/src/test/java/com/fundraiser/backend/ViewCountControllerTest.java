package com.fundraiser.backend;

import com.fundraiser.backend.controller.ViewCountController;
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

class ViewCountControllerTest {

    @Mock
    private FundRaisingActivityRepository fundRaisingActivityRepository;

    @InjectMocks
    private ViewCountController viewCountController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getViewCount_activityExists_returnsCount() {
        // Arrange
        FundRaisingActivity activity = new FundRaisingActivity();
        activity.setViewCount(42);

        when(fundRaisingActivityRepository.findById(1L))
                .thenReturn(Optional.of(activity));

        // Act
        int result = viewCountController.getViewCount("1");

        // Assert
        assertEquals(42, result);
    }

    @Test
    void getViewCount_noViews_returnsZero() {
        // Arrange
        FundRaisingActivity activity = new FundRaisingActivity();
        activity.setViewCount(0);

        when(fundRaisingActivityRepository.findById(2L))
                .thenReturn(Optional.of(activity));

        // Act
        int result = viewCountController.getViewCount("2");

        // Assert
        assertEquals(0, result);
    }

    @Test
    void getViewCount_activityNotFound_returnsZero() {
        // Arrange
        when(fundRaisingActivityRepository.findById(99L))
                .thenReturn(Optional.empty());

        // Act
        int result = viewCountController.getViewCount("99");

        // Assert
        assertEquals(0, result);
    }
}