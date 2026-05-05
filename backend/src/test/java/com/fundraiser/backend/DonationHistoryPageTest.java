package com.fundraiser.backend;

import com.fundraiser.backend.boundary.DonationHistoryPage;
import com.fundraiser.backend.controller.SearchDonationHistoryController;
import com.fundraiser.backend.entity.Donation;
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

class DonationHistoryPageTest {

    @Mock
    private SearchDonationHistoryController searchDonationHistoryController;

    @InjectMocks
    private DonationHistoryPage donationHistoryPage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void clickSearch_matchingResults_returns200() {
        // Arrange
        String doneeId = "donee001";
        String category = "Education";
        String dateFrom = "2024-01-01";
        String dateTo = "2024-12-31";

        Donation donation = new Donation();
        donation.setDoneeId(doneeId);
        donation.setCategory(category);

        when(searchDonationHistoryController.searchDonationHistory(
                doneeId, category, dateFrom, dateTo))
                .thenReturn(Arrays.asList(donation));

        // Act
        ResponseEntity<?> response = donationHistoryPage
                .clickSearch(doneeId, category, dateFrom, dateTo);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(searchDonationHistoryController, times(1))
                .searchDonationHistory(doneeId, category, dateFrom, dateTo);
    }

    @Test
    void clickSearch_noMatches_returns200WithMessage() {
        // Arrange
        String doneeId = "donee001";
        String category = "Sports";
        String dateFrom = "2024-01-01";
        String dateTo = "2024-12-31";

        when(searchDonationHistoryController.searchDonationHistory(
                doneeId, category, dateFrom, dateTo))
                .thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<?> response = donationHistoryPage
                .clickSearch(doneeId, category, dateFrom, dateTo);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(searchDonationHistoryController, times(1))
                .searchDonationHistory(doneeId, category, dateFrom, dateTo);
    }
}