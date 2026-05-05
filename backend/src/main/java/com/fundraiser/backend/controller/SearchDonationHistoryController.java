package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.Donation;
import com.fundraiser.backend.repository.DonationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchDonationHistoryController {

    private final DonationRepository donationRepository;

    // Step 4: searchDonationHistory(donee_id, category, date_from, date_to)
    public List<Donation> searchDonationHistory(
            String doneeId, String category,
            String dateFrom, String dateTo) {

        // Step 5: searchDonationHistory(donee_id, category, date_from, date_to)
        return donationRepository
                .findByDoneeIdAndCategoryAndDonationDateBetween(
                        doneeId, category, dateFrom, dateTo);
    }
}