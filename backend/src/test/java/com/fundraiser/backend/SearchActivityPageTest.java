package com.fundraiser.backend;

import com.fundraiser.backend.boundary.ActivityDashboard;
import com.fundraiser.backend.controller.SearchActivityController;
import com.fundraiser.backend.controller.ViewActivityController;
import com.fundraiser.backend.entity.FundRaisingActivity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchActivityPageTest {

    @Mock
    private ViewActivityController viewActivityController;

    @Mock
    private SearchActivityController searchActivityController;

    @InjectMocks
    private ActivityDashboard activityDashboard;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleSearch_matchingResults_returns200() {
        // Arrange
        String fundRaiserId = "fr001";
        String keyword = "school";

        FundRaisingActivity activity = new FundRaisingActivity();
        activity.setTitle("Help Build a School");

        when(searchActivityController.searchActivities(fundRaiserId, keyword))
                .thenReturn(Arrays.asList(activity));

        // Act
        ResponseEntity<?> response = activityDashboard.handleSearch(fundRaiserId, keyword);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(searchActivityController, times(1)).searchActivities(fundRaiserId, keyword);
    }

    @Test
    void handleSearch_noMatches_returns200WithEmptyMessage() {
        // Arrange
        String fundRaiserId = "fr001";
        String keyword = "xyz";

        when(searchActivityController.searchActivities(fundRaiserId, keyword))
                .thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<?> response = activityDashboard.handleSearch(fundRaiserId, keyword);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
    }
}