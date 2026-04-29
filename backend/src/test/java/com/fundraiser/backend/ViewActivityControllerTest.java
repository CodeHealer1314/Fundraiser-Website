package com.fundraiser.backend;

import com.fundraiser.backend.controller.ViewActivityController;
import com.fundraiser.backend.entity.FundRaisingActivity;
import com.fundraiser.backend.repository.FundRaisingActivityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ViewActivityControllerTest {

    @Mock
    private FundRaisingActivityRepository fundRaisingActivityRepository;

    @InjectMocks
    private ViewActivityController viewActivityController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadActivities_activitiesExist_returnsList() {
        // Arrange
        String fundRaiserId = "fr001";

        FundRaisingActivity activity1 = new FundRaisingActivity();
        activity1.setTitle("Help Build a School");
        activity1.setFundRaiserId(fundRaiserId);

        FundRaisingActivity activity2 = new FundRaisingActivity();
        activity2.setTitle("Clean Water Project");
        activity2.setFundRaiserId(fundRaiserId);

        when(fundRaisingActivityRepository.findByFundRaiserId(fundRaiserId))
                .thenReturn(Arrays.asList(activity1, activity2));

        // Act
        List<FundRaisingActivity> result = viewActivityController.loadActivities(fundRaiserId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(fundRaisingActivityRepository, times(1)).findByFundRaiserId(fundRaiserId);
    }

    @Test
    void loadActivities_noActivities_returnsEmptyList() {
        // Arrange
        String fundRaiserId = "fr002";

        when(fundRaisingActivityRepository.findByFundRaiserId(fundRaiserId))
                .thenReturn(Collections.emptyList());

        // Act
        List<FundRaisingActivity> result = viewActivityController.loadActivities(fundRaiserId);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }
}