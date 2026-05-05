package com.fundraiser.backend;

import com.fundraiser.backend.controller.SearchDonationHistoryController;
import com.fundraiser.backend.entity.Donation;
import com.fundraiser.backend.repository.DonationRepository;
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

class SearchDonationHistoryControllerTest {

    @Mock
    private DonationRepository donationRepository;

    @InjectMocks
    private SearchDonationHistoryController searchDonationHistoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void searchDonationHistory_matchingResults_returnsList() {
        // Arrange
        String doneeId = "donee001";
        String category = "Education";
        String dateFrom = "2024-01-01";
        String dateTo = "2024-12-31";

        Donation donation = new Donation();
        donation.setDoneeId(doneeId);
        donation.setCategory(category);

        when(donationRepository.findByDoneeIdAndCategoryAndDonationDateBetween(
                doneeId, category, dateFrom, dateTo))
                .thenReturn(Arrays.asList(donation));

        // Act
        List<Donation> result = searchDonationHistoryController
                .searchDonationHistory(doneeId, category, dateFrom, dateTo);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Education", result.get(0).getCategory());
    }

    @Test
    void searchDonationHistory_noMatches_returnsEmptyList() {
        // Arrange
        String doneeId = "donee001";
        String category = "Sports";
        String dateFrom = "2024-01-01";
        String dateTo = "2024-12-31";

        when(donationRepository.findByDoneeIdAndCategoryAndDonationDateBetween(
                doneeId, category, dateFrom, dateTo))
                .thenReturn(Collections.emptyList());

        // Act
        List<Donation> result = searchDonationHistoryController
                .searchDonationHistory(doneeId, category, dateFrom, dateTo);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }
}