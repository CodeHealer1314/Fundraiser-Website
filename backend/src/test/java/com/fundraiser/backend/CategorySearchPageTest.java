package com.fundraiser.backend;

import com.fundraiser.backend.boundary.CategorySearchPage;
import com.fundraiser.backend.controller.SearchCategoryController;
import com.fundraiser.backend.entity.ActivityCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategorySearchPageTest {

    @Mock
    private SearchCategoryController searchCategoryController;

    @InjectMocks
    private CategorySearchPage categorySearchPage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleSearch_matchingResults_returns200() {
        // Arrange
        String keyword = "education";

        ActivityCategory category = new ActivityCategory();
        category.setName("Education");
        category.setDescription("Education related activities");

        when(searchCategoryController.searchCategories(keyword))
                .thenReturn(Arrays.asList(category));

        // Act
        ResponseEntity<?> response = categorySearchPage.handleSearch(keyword);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(searchCategoryController, times(1)).searchCategories(keyword);
    }

    @Test
    void handleSearch_noMatches_returns200WithMessage() {
        // Arrange
        String keyword = "xyz";

        when(searchCategoryController.searchCategories(keyword))
                .thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<?> response = categorySearchPage.handleSearch(keyword);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(searchCategoryController, times(1)).searchCategories(keyword);
    }
}