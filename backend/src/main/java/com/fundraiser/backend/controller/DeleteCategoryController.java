package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.ActivityCategory;
import com.fundraiser.backend.repository.ActivityCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeleteCategoryController {

    private final ActivityCategoryRepository activityCategoryRepository;

    // Step 4: handleDelete(category_id)
    public boolean handleDelete(String categoryId) {

        // Step 5: deleteCategory(category_id)
        Optional<ActivityCategory> categoryOpt =
                activityCategoryRepository.findById(Long.parseLong(categoryId));

        // If category not found → return false
        if (categoryOpt.isEmpty()) {
            return false;
        }

        // Delete the category
        activityCategoryRepository.delete(categoryOpt.get());

        // Step 6: return true
        return true;
    }
}