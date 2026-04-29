package com.fundraiser.backend;

import com.fundraiser.backend.controller.SearchHistoryController;
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

class SearchHistoryControllerTest {

    @Mock
    private FundRaisingActivityRepository fundRaisingActivityRepository;

    @InjectMocks
    private SearchHistoryController searchHistoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void searchHistory_matchingResults_returnsList() {
        // Arrange
        String fundRaiserId = "fr001";
        String category = "Education";
        String from = "2024-01-01";
        String to = "2024-12-31";

        FundRaisingActivity activity = new FundRaisingActivity();
        activity.setTitle("Help Build a School");
        activity.setCategory(category);
        activity.setStatus("completed");
        activity.setFundRaiserId(fundRaiserId);

        when(fundRaisingActivityRepository
                .findByFundRaiserIdAndCategoryAndStatusAndCreatedDateBetween(
                        fundRaiserId, category, "completed", from, to))
                .thenReturn(Arrays.asList(activity));

        // Act
        List<FundRaisingActivity> result = searchHistoryController
                .searchHistory(fundRaiserId, category, from, to);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Help Build a School", result.get(0).getTitle());
    }

    @Test
    void searchHistory_noMatches_returnsEmptyList() {
        // Arrange
        String fundRaiserId = "fr001";
        String category = "Sports";
        String from = "2024-01-01";
        String to = "2024-12-31";

        when(fundRaisingActivityRepository
                .findByFundRaiserIdAndCategoryAndStatusAndCreatedDateBetween(
                        fundRaiserId, category, "completed", from, to))
                .thenReturn(Collections.emptyList());

        // Act
        List<FundRaisingActivity> result = searchHistoryController
                .searchHistory(fundRaiserId, category, from, to);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }
}