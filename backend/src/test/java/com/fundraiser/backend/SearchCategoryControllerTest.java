package com.fundraiser.backend;

import com.fundraiser.backend.controller.SearchCategoryController;
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

class SearchCategoryControllerTest {

    @Mock
    private ActivityCategoryRepository activityCategoryRepository;

    @InjectMocks
    private SearchCategoryController searchCategoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void searchCategories_matchingResults_returnsList() {
        // Arrange
        String keyword = "education";

        ActivityCategory category = new ActivityCategory();
        category.setName("Education");
        category.setDescription("Education related activities");

        when(activityCategoryRepository
                .findByNameContainingIgnoreCase(keyword))
                .thenReturn(Arrays.asList(category));

        // Act
        List<ActivityCategory> result =
                searchCategoryController.searchCategories(keyword);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Education", result.get(0).getName());
        verify(activityCategoryRepository, times(1))
                .findByNameContainingIgnoreCase(keyword);
    }

    @Test
    void searchCategories_noMatches_returnsEmptyList() {
        // Arrange
        String keyword = "xyz";

        when(activityCategoryRepository
                .findByNameContainingIgnoreCase(keyword))
                .thenReturn(Collections.emptyList());

        // Act
        List<ActivityCategory> result =
                searchCategoryController.searchCategories(keyword);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
        verify(activityCategoryRepository, times(1))
                .findByNameContainingIgnoreCase(keyword);
    }
}