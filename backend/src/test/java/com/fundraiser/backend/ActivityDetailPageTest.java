package com.fundraiser.backend;

import com.fundraiser.backend.boundary.ActivityDetailPage;
import com.fundraiser.backend.controller.ViewCountController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ActivityDetailPageTest {

    @Mock
    private ViewCountController viewCountController;

    @InjectMocks
    private ActivityDetailPage activityDetailPage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void renderViewCount_activityExists_returns200() {
        // Arrange
        when(viewCountController.getViewCount("1")).thenReturn(42);

        // Act
        ResponseEntity<?> response = activityDetailPage.renderViewCount("1");

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(viewCountController, times(1)).getViewCount("1");
    }

    @Test
    void renderViewCount_noViews_returns200WithZero() {
        // Arrange
        when(viewCountController.getViewCount("2")).thenReturn(0);

        // Act
        ResponseEntity<?> response = activityDetailPage.renderViewCount("2");

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
    }
}