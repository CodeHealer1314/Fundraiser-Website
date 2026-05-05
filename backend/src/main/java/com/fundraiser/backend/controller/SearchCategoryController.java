package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.ActivityCategory;
import com.fundraiser.backend.repository.ActivityCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchCategoryController {

    private final ActivityCategoryRepository activityCategoryRepository;

    // Step 3: searchCategories(keyword)
    public List<ActivityCategory> searchCategories(String keyword) {

        // Step 4: searchCategories(keyword)
        return activityCategoryRepository
                .findByNameContainingIgnoreCase(keyword);
    }
}