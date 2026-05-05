package com.fundraiser.backend;

import com.fundraiser.backend.boundary.SavedActivityPage;
import com.fundraiser.backend.controller.SearchSavedActivityController;
import com.fundraiser.backend.entity.FavouriteActivity;
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

class SavedActivityPageTest {

    @Mock
    private SearchSavedActivityController searchSavedActivityController;

    @InjectMocks
    private SavedActivityPage savedActivityPage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void clickSearch_matchingResults_returns200() {
        // Arrange
        String doneeId = "donee001";
        String keyword = "education";

        FavouriteActivity activity = new FavouriteActivity();
        activity.setDoneeId(doneeId);
        activity.setActivityId("act001");

        when(searchSavedActivityController.searchSavedActivities(doneeId, keyword))
                .thenReturn(Arrays.asList(activity));

        // Act
        ResponseEntity<?> response = savedActivityPage.clickSearch(doneeId, keyword);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(searchSavedActivityController, times(1))
                .searchSavedActivities(doneeId, keyword);
    }

    @Test
    void clickSearch_noMatches_returns200WithMessage() {
        // Arrange
        String doneeId = "donee001";
        String keyword = "xyz";

        when(searchSavedActivityController.searchSavedActivities(doneeId, keyword))
                .thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<?> response = savedActivityPage.clickSearch(doneeId, keyword);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(searchSavedActivityController, times(1))
                .searchSavedActivities(doneeId, keyword);
    }
}