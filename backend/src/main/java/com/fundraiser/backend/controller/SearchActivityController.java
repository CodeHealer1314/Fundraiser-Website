package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.FundRaisingActivity;
import com.fundraiser.backend.repository.FundRaisingActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchActivityController {

    private final FundRaisingActivityRepository fundRaisingActivityRepository;

    // Step 3: searchActivities(fund_raiser_id, keyword)
    public List<FundRaisingActivity> searchActivities(String fundRaiserId, String keyword) {

        // Step 4: searchActivities(fund_raiser_id, keyword)
        return fundRaisingActivityRepository
                .findByFundRaiserIdAndTitleContainingIgnoreCase(fundRaiserId, keyword);
    }
}