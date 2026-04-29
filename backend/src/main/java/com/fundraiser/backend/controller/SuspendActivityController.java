package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.FundRaisingActivity;
import com.fundraiser.backend.repository.FundRaisingActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SuspendActivityController {

    private final FundRaisingActivityRepository fundRaisingActivityRepository;

    // Step 4: handleSuspend(activity_id)
    public boolean handleSuspend(String activityId) {

        // Step 5: suspendActivity(activity_id)
        Optional<FundRaisingActivity> activityOpt = fundRaisingActivityRepository
                .findById(Long.parseLong(activityId));

        // Step 6a: activity not found → return false
        if (activityOpt.isEmpty()) {
            return false;
        }

        FundRaisingActivity activity = activityOpt.get();

        // Step 4: change status to suspended
        activity.setStatus("suspended");
        fundRaisingActivityRepository.save(activity);

        // Step 6: return true
        return true;
    }
}