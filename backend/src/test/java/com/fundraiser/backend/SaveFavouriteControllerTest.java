package com.fundraiser.backend;

import com.fundraiser.backend.controller.SaveFavouriteController;
import com.fundraiser.backend.entity.FavouriteActivity;
import com.fundraiser.backend.repository.FavouriteActivityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SaveFavouriteControllerTest {

    @Mock
    private FavouriteActivityRepository favouriteActivityRepository;

    @InjectMocks
    private SaveFavouriteController saveFavouriteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleSaveFavourite_validInput_returnsTrue() {
        // Arrange
        String doneeId = "donee001";
        String activityId = "act001";

        FavouriteActivity savedActivity = new FavouriteActivity();
        savedActivity.setDoneeId(doneeId);
        savedActivity.setActivityId(activityId);

        when(favouriteActivityRepository.save(any(FavouriteActivity.class)))
                .thenReturn(savedActivity);

        // Act
        boolean result = saveFavouriteController
                .handleSaveFavourite(doneeId, activityId);

        // Assert
        assertTrue(result);
        verify(favouriteActivityRepository, times(1))
                .save(any(FavouriteActivity.class));
    }

    @Test
    void handleSaveFavourite_saveFails_returnsFalse() {
        // Arrange
        String doneeId = "donee001";
        String activityId = "act001";

        when(favouriteActivityRepository.save(any(FavouriteActivity.class)))
                .thenThrow(new RuntimeException("Database error"));

        // Act
        boolean result = saveFavouriteController
                .handleSaveFavourite(doneeId, activityId);

        // Assert
        assertFalse(result);
        verify(favouriteActivityRepository, times(1))
                .save(any(FavouriteActivity.class));
    }
}
