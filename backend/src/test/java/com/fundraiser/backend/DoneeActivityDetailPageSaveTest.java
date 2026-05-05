package com.fundraiser.backend;

import com.fundraiser.backend.boundary.DoneeActivityDetailPage;
import com.fundraiser.backend.controller.DoneeViewActivityController;
import com.fundraiser.backend.controller.SaveFavouriteController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DoneeActivityDetailPageSaveTest {

    @Mock
    private DoneeViewActivityController doneeViewActivityController;

    @Mock
    private SaveFavouriteController saveFavouriteController;

    @InjectMocks
    private DoneeActivityDetailPage doneeActivityDetailPage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void clickSave_validInput_returns200() {
        // Arrange
        String doneeId = "donee001";
        String activityId = "act001";

        when(saveFavouriteController.handleSaveFavourite(doneeId, activityId))
                .thenReturn(true);

        // Act
        ResponseEntity<?> response =
                doneeActivityDetailPage.clickSave(doneeId, activityId);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(saveFavouriteController, times(1))
                .handleSaveFavourite(doneeId, activityId);
    }

    @Test
    void clickSave_saveFails_returns400() {
        // Arrange
        String doneeId = "donee001";
        String activityId = "act001";

        when(saveFavouriteController.handleSaveFavourite(doneeId, activityId))
                .thenReturn(false);

        // Act
        ResponseEntity<?> response =
                doneeActivityDetailPage.clickSave(doneeId, activityId);

        // Assert
        assertNotNull(response);
        assertEquals(400, response.getStatusCode().value());
        verify(saveFavouriteController, times(1))
                .handleSaveFavourite(doneeId, activityId);
    }
}