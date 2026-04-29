package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.FundRaisingActivity;
import com.fundraiser.backend.repository.FundRaisingActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchHistoryController {

    private final FundRaisingActivityRepository fundRaisingActivityRepository;

    // Step 4: searchHistory(fund_raiser_id, category, from, to)
    public List<FundRaisingActivity> searchHistory(
            String fundRaiserId,
            String category,
            String from,
            String to) {

        // Step 5: searchHistory(fund_raiser_id, category, from, to)
        return fundRaisingActivityRepository
                .findByFundRaiserIdAndCategoryAndStatusAndCreatedDateBetween(
                        fundRaiserId, category, "completed", from, to);
    }
}
