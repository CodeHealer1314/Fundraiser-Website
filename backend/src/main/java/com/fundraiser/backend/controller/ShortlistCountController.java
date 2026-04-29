package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.FundRaisingActivity;
import com.fundraiser.backend.repository.FundRaisingActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShortlistCountController {

    private final FundRaisingActivityRepository fundRaisingActivityRepository;

    // Step 2: getShortlistCount(activity_id)
    public int getShortlistCount(String activityId) {

        // Step 3: getShortlistCount(activity_id)
        Optional<FundRaisingActivity> activityOpt = fundRaisingActivityRepository
                .findById(Long.parseLong(activityId));

        // Step 4a: activity not found → return 0
        if (activityOpt.isEmpty()) {
            return 0;
        }

        // Step 4: return int count
        return activityOpt.get().getShortlistCount();
    }
}