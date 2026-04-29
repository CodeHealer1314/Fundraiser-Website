package com.fundraiser.backend;

import com.fundraiser.backend.boundary.ActivityDetailPage;
import com.fundraiser.backend.controller.ShortlistCountController;
import com.fundraiser.backend.controller.ViewCountController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShortlistCountPageTest {

    @Mock
    private ViewCountController viewCountController;

    @Mock
    private ShortlistCountController shortlistCountController;

    @InjectMocks
    private ActivityDetailPage activityDetailPage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void renderShortlistCount_activityExists_returns200() {
        // Arrange
        when(shortlistCountController.getShortlistCount("1")).thenReturn(15);

        // Act
        ResponseEntity<?> response = activityDetailPage.renderShortlistCount("1");

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        verify(shortlistCountController, times(1)).getShortlistCount("1");
    }

    @Test
    void renderShortlistCount_noShortlists_returns200WithZero() {
        // Arrange
        when(shortlistCountController.getShortlistCount("2")).thenReturn(0);

        // Act
        ResponseEntity<?> response = activityDetailPage.renderShortlistCount("2");

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
    }
}