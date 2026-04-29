package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.FundRaisingActivity;
import com.fundraiser.backend.repository.FundRaisingActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ViewCompletedController {

    private final FundRaisingActivityRepository fundRaisingActivityRepository;

    // Step 3: viewCompletedActivity(activity_id)
    public FundRaisingActivity loadCompletedActivity(String activityId) {

        // Step 3: viewCompletedActivity(activity_id)
        Optional<FundRaisingActivity> activityOpt = fundRaisingActivityRepository
                .findById(Long.parseLong(activityId));

        // Step 4a: activity not found → return null
        if (activityOpt.isEmpty()) {
            return null;
        }

        FundRaisingActivity activity = activityOpt.get();

        // Only return if activity is completed
        if (!activity.getStatus().equals("completed")) {
            return null;
        }

        // Step 4: return activity
        return activity;
    }
}
