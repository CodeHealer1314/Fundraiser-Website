package com.fundraiser.backend;

import com.fundraiser.backend.controller.ShortlistCountController;
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

class ShortlistCountControllerTest {

    @Mock
    private FundRaisingActivityRepository fundRaisingActivityRepository;

    @InjectMocks
    private ShortlistCountController shortlistCountController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getShortlistCount_activityExists_returnsCount() {
        // Arrange
        FundRaisingActivity activity = new FundRaisingActivity();
        activity.setShortlistCount(15);

        when(fundRaisingActivityRepository.findById(1L))
                .thenReturn(Optional.of(activity));

        // Act
        int result = shortlistCountController.getShortlistCount("1");

        // Assert
        assertEquals(15, result);
    }

    @Test
    void getShortlistCount_noShortlists_returnsZero() {
        // Arrange
        FundRaisingActivity activity = new FundRaisingActivity();
        activity.setShortlistCount(0);

        when(fundRaisingActivityRepository.findById(2L))
                .thenReturn(Optional.of(activity));

        // Act
        int result = shortlistCountController.getShortlistCount("2");

        // Assert
        assertEquals(0, result);
    }

    @Test
    void getShortlistCount_activityNotFound_returnsZero() {
        // Arrange
        when(fundRaisingActivityRepository.findById(99L))
                .thenReturn(Optional.empty());

        // Act
        int result = shortlistCountController.getShortlistCount("99");

        // Assert
        assertEquals(0, result);
    }
}