package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.FundRaisingActivity;
import com.fundraiser.backend.repository.FundRaisingActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoneeViewActivityController {

    private final FundRaisingActivityRepository fundRaisingActivityRepository;

    // Step 3: loadActivityDetails(activity_id)
    public FundRaisingActivity loadActivityDetails(String activityId) {

        // Step 4: viewActivityDetails(activity_id)
        Optional<FundRaisingActivity> activityOpt =
                fundRaisingActivityRepository.findById(Long.parseLong(activityId));

        // If activity not found → return null
        if (activityOpt.isEmpty()) {
            return null;
        }

        // Step 4: return details
        return activityOpt.get();
    }
}
