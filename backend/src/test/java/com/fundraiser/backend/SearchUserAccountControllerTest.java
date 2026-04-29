package com.fundraiser.backend;

import com.fundraiser.backend.controller.SearchUserAccountController;
import com.fundraiser.backend.entity.UserAccount;
import com.fundraiser.backend.repository.UserAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchUserAccountControllerTest {

    @Mock
    private UserAccountRepository userAccountRepository;

    @InjectMocks
    private SearchUserAccountController searchUserAccountController;

    private List<UserAccount> mockAccounts;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockAccounts = new ArrayList<>();

        UserAccount account1 = new UserAccount();
        account1.setAccountId("acc-001");
        account1.setUsername("johndoe");
        account1.setEmail("john@test.com");
        account1.setRole("FUND_RAISER");

        UserAccount account2 = new UserAccount();
        account2.setAccountId("acc-002");
        account2.setUsername("johnsmith");
        account2.setEmail("johnsmith@test.com");
        account2.setRole("DONEE");

        mockAccounts.add(account1);
        mockAccounts.add(account2);
    }

    @Test
    void searchAccounts_keywordMatches_returnsList() {
        // Arrange
        when(userAccountRepository.searchAccounts("john"))
                .thenReturn(mockAccounts);

        // Act
        List<UserAccount> result = searchUserAccountController.searchAccounts("john");

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("johndoe", result.get(0).getUsername());
        assertEquals("johnsmith", result.get(1).getUsername());
    }

    @Test
    void searchAccounts_noMatches_returnsEmptyList() {
        // Arrange
        when(userAccountRepository.searchAccounts("unknown"))
                .thenReturn(new ArrayList<>());

        // Act
        List<UserAccount> result = searchUserAccountController.searchAccounts("unknown");

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void searchAccounts_emptyKeyword_returnsEmptyList() {
        // Arrange
        when(userAccountRepository.searchAccounts(""))
                .thenReturn(new ArrayList<>());

        // Act
        List<UserAccount> result = searchUserAccountController.searchAccounts("");

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }
}
