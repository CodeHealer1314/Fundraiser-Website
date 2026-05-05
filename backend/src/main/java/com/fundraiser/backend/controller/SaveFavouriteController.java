package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.FavouriteActivity;
import com.fundraiser.backend.repository.FavouriteActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveFavouriteController {

    private final FavouriteActivityRepository favouriteActivityRepository;

    // Step 2: handleSaveFavourite(donee_id, activity_id)
    public boolean handleSaveFavourite(String doneeId, String activityId) {

        try {
            // Step 3: saveFavourite(donee_id, activity_id)
            FavouriteActivity favourite = new FavouriteActivity();
            favourite.setDoneeId(doneeId);
            favourite.setActivityId(activityId);

            favouriteActivityRepository.save(favourite);

            // Step 4: return true
            return true;

        } catch (Exception e) {
            // Save failed → return false
            return false;
        }
    }
}