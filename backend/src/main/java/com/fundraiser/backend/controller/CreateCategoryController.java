package com.fundraiser.backend.controller;

import com.fundraiser.backend.boundary.CreateCategoryPage;
import com.fundraiser.backend.entity.ActivityCategory;
import com.fundraiser.backend.repository.ActivityCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCategoryController {

    private final ActivityCategoryRepository activityCategoryRepository;

    // Step 3: validateCategory(data)
    public boolean validateCategory(Object data) {
        if (data instanceof CreateCategoryPage.CategoryRequest request) {
            // Validate name and description are not null or empty
            return request.getName() != null && !request.getName().isEmpty()
                    && request.getDescription() != null
                    && !request.getDescription().isEmpty();
        }
        return false;
    }

    // Step 5: createCategory(data)
    public ActivityCategory createCategory(Object data) {
        if (data instanceof CreateCategoryPage.CategoryRequest request) {

            // Step 6: saveCategory(data)
            ActivityCategory category = new ActivityCategory();
            category.setName(request.getName());
            category.setDescription(request.getDescription());

            // Step 7: return category
            return activityCategoryRepository.save(category);
        }
        return null;
    }
}