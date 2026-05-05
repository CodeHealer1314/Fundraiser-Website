package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.FavouriteActivity;
import com.fundraiser.backend.repository.FavouriteActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchSavedActivityController {

    private final FavouriteActivityRepository favouriteActivityRepository;

    // Step 3: searchSavedActivities(donee_id, keyword)
    public List<FavouriteActivity> searchSavedActivities(
            String doneeId, String keyword) {

        // Step 4: searchSavedActivities(donee_id, keyword)
        return favouriteActivityRepository
                .findByDoneeIdAndActivityIdContainingIgnoreCase(doneeId, keyword);
    }
}