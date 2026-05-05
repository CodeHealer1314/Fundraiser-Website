package com.fundraiser.backend;

import com.fundraiser.backend.controller.ViewCategoryController;
import com.fundraiser.backend.entity.ActivityCategory;
import com.fundraiser.backend.repository.ActivityCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ViewCategoryControllerTest {

    @Mock
    private ActivityCategoryRepository activityCategoryRepository;

    @InjectMocks
    private ViewCategoryController viewCategoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadCategories_categoriesFound_returnsList() {
        // Arrange
        ActivityCategory category = new ActivityCategory();
        category.setName("Education");
        category.setDescription("Education related activities");

        when(activityCategoryRepository.findAll())
                .thenReturn(Arrays.asList(category));

        // Act
        List<ActivityCategory> result = viewCategoryController.loadCategories();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Education", result.get(0).getName());
        verify(activityCategoryRepository, times(1)).findAll();
    }

    @Test
    void loadCategories_noCategories_returnsEmptyList() {
        // Arrange
        when(activityCategoryRepository.findAll())
                .thenReturn(Collections.emptyList());

        // Act
        List<ActivityCategory> result = viewCategoryController.loadCategories();

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
        verify(activityCategoryRepository, times(1)).findAll();
    }
}