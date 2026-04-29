package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.FundRaisingActivity;
import com.fundraiser.backend.repository.FundRaisingActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateActivityController {

    private final FundRaisingActivityRepository fundRaisingActivityRepository;

    // Step 2: loadActivity(activity_id)
    public FundRaisingActivity loadActivity(String activityId) {

        // Step 3: getActivity(activity_id)
        Optional<FundRaisingActivity> activityOpt = fundRaisingActivityRepository
                .findById(Long.parseLong(activityId));

        // Step 4a: activity not found → return null
        if (activityOpt.isEmpty()) {
            return null;
        }

        // Step 4: return activity
        return activityOpt.get();
    }

    // Step 8: updateActivity(activity_id, data)
    public boolean updateActivity(String activityId, Object data) {

        Optional<FundRaisingActivity> activityOpt = fundRaisingActivityRepository
                .findById(Long.parseLong(activityId));

        // Step 10a: activity not found → return false
        if (activityOpt.isEmpty()) {
            return false;
        }

        FundRaisingActivity existing = activityOpt.get();
        FundRaisingActivity updated = (FundRaisingActivity) data;

        // Update fields
        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setCategory(updated.getCategory());
        existing.setGoalAmount(updated.getGoalAmount());

        // Step 9: updateActivity(activity_id, data)
        fundRaisingActivityRepository.save(existing);

        // Step 10: return true
        return true;
    }
}