package com.fundraiser.backend;

import com.fundraiser.backend.controller.SuspendUserAccountController;
import com.fundraiser.backend.entity.UserAccount;
import com.fundraiser.backend.repository.UserAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SuspendUserAccountControllerTest {

    @Mock
    private UserAccountRepository userAccountRepository;

    @InjectMocks
    private SuspendUserAccountController suspendUserAccountController;

    private UserAccount mockAccount;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockAccount = new UserAccount();
        mockAccount.setAccountId("acc-001");
        mockAccount.setUsername("johndoe");
        mockAccount.setEmail("john@test.com");
        mockAccount.setRole("FUND_RAISER");
        mockAccount.setSuspended(false);
    }

    @Test
    void handleSuspend_accountExists_returnsTrue() {
        // Arrange
        when(userAccountRepository.findById("acc-001"))
                .thenReturn(Optional.of(mockAccount));
        when(userAccountRepository.save(any(UserAccount.class)))
                .thenReturn(mockAccount);

        // Act
        boolean result = suspendUserAccountController.handleSuspend("acc-001");

        // Assert
        assertTrue(result);
        assertTrue(mockAccount.isSuspended());
        verify(userAccountRepository, times(1)).save(mockAccount);
    }

    @Test
    void handleSuspend_accountNotFound_returnsFalse() {
        // Arrange
        when(userAccountRepository.findById("unknown-id"))
                .thenReturn(Optional.empty());

        // Act
        boolean result = suspendUserAccountController.handleSuspend("unknown-id");

        // Assert
        assertFalse(result);
    }

    @Test
    void handleSuspend_alreadySuspended_returnsFalse() {
        // Arrange
        mockAccount.setSuspended(true);
        when(userAccountRepository.findById("acc-001"))
                .thenReturn(Optional.of(mockAccount));

        // Act
        boolean result = suspendUserAccountController.handleSuspend("acc-001");

        // Assert
        assertFalse(result);
    }
}