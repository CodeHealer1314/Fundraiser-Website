package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.SearchUserAccountController;
import com.fundraiser.backend.entity.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserAccountSearchPage {

    private final SearchUserAccountController searchUserAccountController;

    @GetMapping("/accounts/search")
    public ResponseEntity<?> handleSearch(@RequestParam String keyword) {
        // Step 3: searchAccounts(keyword)
        List<UserAccount> results = searchUserAccountController.searchAccounts(keyword);

        // Step 7a: no accounts found
        if (results.isEmpty()) {
            return ResponseEntity.ok(Map.of("message", "No user accounts found"));
        }

        // Step 7: renderAccountList(results)
        return ResponseEntity.ok(results);
    }
}
