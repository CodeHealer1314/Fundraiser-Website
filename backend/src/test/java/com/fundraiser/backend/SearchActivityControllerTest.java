package com.fundraiser.backend;

import com.fundraiser.backend.controller.SearchActivityController;
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

class SearchActivityControllerTest {

    @Mock
    private FundRaisingActivityRepository fundRaisingActivityRepository;

    @InjectMocks
    private SearchActivityController searchActivityController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void searchActivities_matchingResults_returnsList() {
        // Arrange
        String fundRaiserId = "fr001";
        String keyword = "school";

        FundRaisingActivity activity = new FundRaisingActivity();
        activity.setTitle("Help Build a School");
        activity.setFundRaiserId(fundRaiserId);

        when(fundRaisingActivityRepository
                .findByFundRaiserIdAndTitleContainingIgnoreCase(fundRaiserId, keyword))
                .thenReturn(Arrays.asList(activity));

        // Act
        List<FundRaisingActivity> result = searchActivityController
                .searchActivities(fundRaiserId, keyword);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Help Build a School", result.get(0).getTitle());
    }

    @Test
    void searchActivities_noMatches_returnsEmptyList() {
        // Arrange
        String fundRaiserId = "fr001";
        String keyword = "xyz";

        when(fundRaisingActivityRepository
                .findByFundRaiserIdAndTitleContainingIgnoreCase(fundRaiserId, keyword))
                .thenReturn(Collections.emptyList());

        // Act
        List<FundRaisingActivity> result = searchActivityController
                .searchActivities(fundRaiserId, keyword);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }
}
