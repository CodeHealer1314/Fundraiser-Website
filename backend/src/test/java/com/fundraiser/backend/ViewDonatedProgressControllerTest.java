package com.fundraiser.backend;

import com.fundraiser.backend.controller.ViewDonatedProgressController;
import com.fundraiser.backend.entity.Donation;
import com.fundraiser.backend.entity.FundRaisingActivity;
import com.fundraiser.backend.repository.DonationRepository;
import com.fundraiser.backend.repository.FundRaisingActivityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ViewDonatedProgressControllerTest {

    @Mock
    private DonationRepository donationRepository;

    @Mock
    private FundRaisingActivityRepository fundRaisingActivityRepository;

    @InjectMocks
    private ViewDonatedProgressController viewDonatedProgressController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getDonatedActivityProgress_activitiesFound_returnsList() {
        // Arrange
        String doneeId = "donee001";

        Donation donation = new Donation();
        donation.setDoneeId(doneeId);
        donation.setActivityId("1");

        FundRaisingActivity activity = new FundRaisingActivity();
        activity.setTitle("Help Build a School");

        when(donationRepository.findByDoneeId(doneeId))
                .thenReturn(Arrays.asList(donation));
        when(fundRaisingActivityRepository.findById(1L))
                .thenReturn(Optional.of(activity));

        // Act
        List<FundRaisingActivity> result =
                viewDonatedProgressController.getDonatedActivityProgress(doneeId);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Help Build a School", result.get(0).getTitle());
        verify(donationRepository, times(1)).findByDoneeId(doneeId);
    }

    @Test
    void getDonatedActivityProgress_noActivities_returnsEmptyList() {
        // Arrange
        String doneeId = "donee001";

        when(donationRepository.findByDoneeId(doneeId))
                .thenReturn(Collections.emptyList());

        // Act
        List<FundRaisingActivity> result =
                viewDonatedProgressController.getDonatedActivityProgress(doneeId);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
        verify(donationRepository, times(1)).findByDoneeId(doneeId);
    }
}