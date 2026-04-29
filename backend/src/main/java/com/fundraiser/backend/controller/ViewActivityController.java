package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.FundRaisingActivity;
import com.fundraiser.backend.repository.FundRaisingActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViewActivityController {

    private final FundRaisingActivityRepository fundRaisingActivityRepository;

    // Step 2: loadActivities(fund_raiser_id)
    public List<FundRaisingActivity> loadActivities(String fundRaiserId) {

        // Step 3: getActivities(fund_raiser_id)
        return fundRaisingActivityRepository.findByFundRaiserId(fundRaiserId);
    }
}