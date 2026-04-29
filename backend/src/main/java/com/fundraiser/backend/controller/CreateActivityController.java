package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.FundRaisingActivity;
import com.fundraiser.backend.repository.FundRaisingActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateActivityController {

    private final FundRaisingActivityRepository fundRaisingActivityRepository;

    // Step 3: validateActivity(data)
    public boolean validateActivity(Object data) {
        if (data == null) {
            return false;
        }

        FundRaisingActivity activity = (FundRaisingActivity) data;

        // Validate required fields
        if (activity.getTitle() == null || activity.getTitle().isBlank()) {
            return false;
        }
        if (activity.getDescription() == null || activity.getDescription().isBlank()) {
            return false;
        }
        if (activity.getCategory() == null || activity.getCategory().isBlank()) {
            return false;
        }
        if (activity.getGoalAmount() <= 0) {
            return false;
        }
        if (activity.getFundRaiserId() == null || activity.getFundRaiserId().isBlank()) {
            return false;
        }

        return true;
    }

    // Step 5: createActivity(data)
    public FundRaisingActivity createActivity(Object data) {
        if (data == null) {
            return null;
        }

        FundRaisingActivity activity = (FundRaisingActivity) data;

        // Set default values
        activity.setStatus("active");
        activity.setViewCount(0);
        activity.setShortlistCount(0);

        // Step 6: saveActivity(data)
        return fundRaisingActivityRepository.save(activity);
    }
}