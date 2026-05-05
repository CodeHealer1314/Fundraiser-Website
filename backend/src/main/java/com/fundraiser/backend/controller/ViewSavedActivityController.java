package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.FundRaisingActivity;
import com.fundraiser.backend.repository.FundRaisingActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ViewSavedActivityController {

    private final FundRaisingActivityRepository fundRaisingActivityRepository;

    // Step 2: loadSavedActivityDetails(donee_id, activity_id)
    public FundRaisingActivity loadSavedActivityDetails(
            String doneeId, String activityId) {

        // Step 3: viewSavedActivityDetails(activity_id)
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