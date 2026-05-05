package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.Donation;
import com.fundraiser.backend.entity.FundRaisingActivity;
import com.fundraiser.backend.repository.DonationRepository;
import com.fundraiser.backend.repository.FundRaisingActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ViewDonatedProgressController {

    private final DonationRepository donationRepository;
    private final FundRaisingActivityRepository fundRaisingActivityRepository;

    // Step 2: getDonatedActivityProgress(donee_id)
    public List<FundRaisingActivity> getDonatedActivityProgress(String doneeId) {

        // Step 3: getProgressForDoneeActivities(donee_id)
        List<Donation> donations = donationRepository.findByDoneeId(doneeId);

        // If no donations found → return empty list
        if (donations == null || donations.isEmpty()) {
            return new ArrayList<>();
        }

        // Get activity details for each donation
        List<FundRaisingActivity> activities = new ArrayList<>();
        for (Donation donation : donations) {
            Optional<FundRaisingActivity> activityOpt =
                    fundRaisingActivityRepository
                            .findById(Long.parseLong(donation.getActivityId()));
            activityOpt.ifPresent(activities::add);
        }

        // Step 4: return List<Activity Progress>
        return activities;
    }
}