package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.ActivityCategory;
import com.fundraiser.backend.repository.ActivityCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViewCategoryController {

    private final ActivityCategoryRepository activityCategoryRepository;

    // Step 3: getAllCategories()
    public List<ActivityCategory> loadCategories() {

        // Step 4: return List<Category>
        return activityCategoryRepository.findAll();
    }
}