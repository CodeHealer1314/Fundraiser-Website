package com.fundraiser.backend;

import com.fundraiser.backend.controller.SearchSavedActivityController;
import com.fundraiser.backend.entity.FavouriteActivity;
import com.fundraiser.backend.repository.FavouriteActivityRepository;
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

class SearchSavedActivityControllerTest {

    @Mock
    private FavouriteActivityRepository favouriteActivityRepository;

    @InjectMocks
    private SearchSavedActivityController searchSavedActivityController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void searchSavedActivities_matchingResults_returnsList() {
        // Arrange
        String doneeId = "donee001";
        String keyword = "education";

        FavouriteActivity activity = new FavouriteActivity();
        activity.setDoneeId(doneeId);
        activity.setActivityId("act001");

        when(favouriteActivityRepository
                .findByDoneeIdAndActivityIdContainingIgnoreCase(doneeId, keyword))
                .thenReturn(Arrays.asList(activity));

        // Act
        List<FavouriteActivity> result =
                searchSavedActivityController.searchSavedActivities(doneeId, keyword);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("act001", result.get(0).getActivityId());
    }

    @Test
    void searchSavedActivities_noMatches_returnsEmptyList() {
        // Arrange
        String doneeId = "donee001";
        String keyword = "xyz";

        when(favouriteActivityRepository
                .findByDoneeIdAndActivityIdContainingIgnoreCase(doneeId, keyword))
                .thenReturn(Collections.emptyList());

        // Act
        List<FavouriteActivity> result =
                searchSavedActivityController.searchSavedActivities(doneeId, keyword);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }
}