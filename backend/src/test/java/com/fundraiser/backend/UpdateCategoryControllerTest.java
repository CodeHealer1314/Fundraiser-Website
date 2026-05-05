package com.fundraiser.backend;

import com.fundraiser.backend.boundary.EditCategoryPage;
import com.fundraiser.backend.controller.UpdateCategoryController;
import com.fundraiser.backend.entity.ActivityCategory;
import com.fundraiser.backend.repository.ActivityCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateCategoryControllerTest {

    @Mock
    private ActivityCategoryRepository activityCategoryRepository;

    @InjectMocks
    private UpdateCategoryController updateCategoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadCategory_categoryFound_returnsCategory() {
        // Arrange
        String categoryId = "1";

        ActivityCategory category = new ActivityCategory();
        category.setName("Education");
        category.setDescription("Education related activities");

        when(activityCategoryRepository.findById(1L))
                .thenReturn(Optional.of(category));

        // Act
        ActivityCategory result =
                updateCategoryController.loadCategory(categoryId);

        // Assert
        assertNotNull(result);
        assertEquals("Education", result.getName());
        verify(activityCategoryRepository, times(1)).findById(1L);
    }

    @Test
    void updateCategory_validData_returnsTrue() {
        // Arrange
        String categoryId = "1";

        EditCategoryPage.CategoryRequest request =
                new EditCategoryPage.CategoryRequest();
        request.setName("Education Updated");
        request.setDescription("Updated description");

        ActivityCategory category = new ActivityCategory();
        category.setName("Education");
        category.setDescription("Education related activities");

        when(activityCategoryRepository.findById(1L))
                .thenReturn(Optional.of(category));
        when(activityCategoryRepository.save(any(ActivityCategory.class)))
                .thenReturn(category);

        // Act
        boolean result =
                updateCategoryController.updateCategory(categoryId, request);

        // Assert
        assertTrue(result);
        verify(activityCategoryRepository, times(1)).findById(1L);
        verify(activityCategoryRepository, times(1))
                .save(any(ActivityCategory.class));
    }
}