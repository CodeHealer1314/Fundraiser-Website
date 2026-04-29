package com.fundraiser.backend;

import com.fundraiser.backend.boundary.CompletedHistoryPage;
import com.fundraiser.backend.controller.SearchHistoryController;
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

class CompletedHistoryPageTest {

    @Mock
    private SearchHistoryController searchHistoryController;

    @InjectMocks
    private CompletedHistoryPage completedHistoryPage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleSearch_matchingResults_returns200() {
        // Arrange
        String fundRaiserId = "fr001";
        String category = "Education";
        String from = "2024-01-01";
        String to = "2024-12-31";

        FundRaisingActivity activity = new FundRaisingActivity();
        activity.setTitle("Help Build a School");

        when(searchHistoryController.searchHistory(fundRaiserId, category, from, to))
                .thenReturn(Arrays.asList(activity));

        // Act
        ResponseEntity<?> response = completedHistoryPage
                .handleSearch(fundRaiserId, category, from, to);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(searchHistoryController, times(1))
                .searchHistory(fundRaiserId, category, from, to);
    }

    @Test
    void handleSearch_noMatches_returns200WithEmptyMessage() {
        // Arrange
        String fundRaiserId = "fr001";
        String category = "Sports";
        String from = "2024-01-01";
        String to = "2024-12-31";

        when(searchHistoryController.searchHistory(fundRaiserId, category, from, to))
                .thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<?> response = completedHistoryPage
                .handleSearch(fundRaiserId, category, from, to);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
    }
}