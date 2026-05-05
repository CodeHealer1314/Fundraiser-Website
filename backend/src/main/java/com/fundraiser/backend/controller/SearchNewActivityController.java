package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.FundRaisingActivity;
import com.fundraiser.backend.repository.FundRaisingActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchNewActivityController {

    private final FundRaisingActivityRepository fundRaisingActivityRepository;

    // Step 3: searchActivities(keyword)
    public List<FundRaisingActivity> searchActivities(String keyword) {

        // Step 4: searchAvailableActivities(keyword)
        return fundRaisingActivityRepository
                .findByTitleContainingIgnoreCaseAndStatus(keyword, "active");
    }
}