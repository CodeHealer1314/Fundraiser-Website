package com.fundraiser.backend;

import com.fundraiser.backend.controller.DeleteCategoryController;
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

class DeleteCategoryControllerTest {

    @Mock
    private ActivityCategoryRepository activityCategoryRepository;

    @InjectMocks
    private DeleteCategoryController deleteCategoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleDelete_categoryFound_returnsTrue() {
        // Arrange
        String categoryId = "1";

        ActivityCategory category = new ActivityCategory();
        category.setName("Education");
        category.setDescription("Education related activities");

        when(activityCategoryRepository.findById(1L))
                .thenReturn(Optional.of(category));

        // Act
        boolean result = deleteCategoryController.handleDelete(categoryId);

        // Assert
        assertTrue(result);
        verify(activityCategoryRepository, times(1)).findById(1L);
        verify(activityCategoryRepository, times(1)).delete(category);
    }

    @Test
    void handleDelete_categoryNotFound_returnsFalse() {
        // Arrange
        String categoryId = "999";

        when(activityCategoryRepository.findById(999L))
                .thenReturn(Optional.empty());

        // Act
        boolean result = deleteCategoryController.handleDelete(categoryId);

        // Assert
        assertFalse(result);
        verify(activityCategoryRepository, times(1)).findById(999L);
        verify(activityCategoryRepository, never()).delete(any());
    }
}