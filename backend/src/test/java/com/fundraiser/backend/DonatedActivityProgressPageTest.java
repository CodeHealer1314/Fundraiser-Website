package com.fundraiser.backend;

import com.fundraiser.backend.boundary.DonatedActivityProgressPage;
import com.fundraiser.backend.controller.ViewDonatedProgressController;
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

class DonatedActivityProgressPageTest {

    @Mock
    private ViewDonatedProgressController viewDonatedProgressController;

    @InjectMocks
    private DonatedActivityProgressPage donatedActivityProgressPage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void openProgressPage_activitiesFound_returns200() {
        // Arrange
        String doneeId = "donee001";

        FundRaisingActivity activity = new FundRaisingActivity();
        activity.setTitle("Help Build a School");

        when(viewDonatedProgressController.getDonatedActivityProgress(doneeId))
                .thenReturn(Arrays.asList(activity));

        // Act
        ResponseEntity<?> response =
                donatedActivityProgressPage.openProgressPage(doneeId);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(viewDonatedProgressController, times(1))
                .getDonatedActivityProgress(doneeId);
    }

    @Test
    void openProgressPage_noActivities_returns200WithMessage() {
        // Arrange
        String doneeId = "donee001";

        when(viewDonatedProgressController.getDonatedActivityProgress(doneeId))
                .thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<?> response =
                donatedActivityProgressPage.openProgressPage(doneeId);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(viewDonatedProgressController, times(1))
                .getDonatedActivityProgress(doneeId);
    }
}