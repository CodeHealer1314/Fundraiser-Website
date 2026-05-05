package com.fundraiser.backend;

import com.fundraiser.backend.boundary.ActivitySearchPage;
import com.fundraiser.backend.controller.SearchNewActivityController;
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

class ActivitySearchPageTest {

    @Mock
    private SearchNewActivityController searchNewActivityController;

    @InjectMocks
    private ActivitySearchPage activitySearchPage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void clickSearch_matchingResults_returns200() {
        // Arrange
        String keyword = "education";

        FundRaisingActivity activity = new FundRaisingActivity();
        activity.setTitle("Help Build a School");

        when(searchNewActivityController.searchActivities(keyword))
                .thenReturn(Arrays.asList(activity));

        // Act
        ResponseEntity<?> response = activitySearchPage.clickSearch(keyword);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(searchNewActivityController, times(1))
                .searchActivities(keyword);
    }

    @Test
    void clickSearch_noMatches_returns200WithMessage() {
        // Arrange
        String keyword = "xyz";

        when(searchNewActivityController.searchActivities(keyword))
                .thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<?> response = activitySearchPage.clickSearch(keyword);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(searchNewActivityController, times(1))
                .searchActivities(keyword);
    }
}