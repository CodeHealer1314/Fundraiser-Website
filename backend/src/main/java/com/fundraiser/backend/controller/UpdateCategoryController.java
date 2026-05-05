package com.fundraiser.backend.controller;

import com.fundraiser.backend.boundary.EditCategoryPage;
import com.fundraiser.backend.entity.ActivityCategory;
import com.fundraiser.backend.repository.ActivityCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateCategoryController {

    private final ActivityCategoryRepository activityCategoryRepository;

    // Step 3: loadCategory(category_id)
    public ActivityCategory loadCategory(String categoryId) {

        // Step 4: getCategory(category_id)
        Optional<ActivityCategory> categoryOpt =
                activityCategoryRepository.findById(Long.parseLong(categoryId));

        // If category not found → return null
        if (categoryOpt.isEmpty()) {
            return null;
        }

        // Step 5: return category
        return categoryOpt.get();
    }

    // Step 8: updateCategory(category_id, data)
    public boolean updateCategory(String categoryId, Object data) {

        if (data instanceof EditCategoryPage.CategoryRequest request) {

            // Step 9: getCategory(category_id)
            Optional<ActivityCategory> categoryOpt =
                    activityCategoryRepository
                            .findById(Long.parseLong(categoryId));

            if (categoryOpt.isEmpty()) {
                return false;
            }

            ActivityCategory category = categoryOpt.get();
            category.setName(request.getName());
            category.setDescription(request.getDescription());

            // Step 10: updateCategory(category_id, data)
            activityCategoryRepository.save(category);

            // Step 11: return true
            return true;
        }
        return false;
    }
}
