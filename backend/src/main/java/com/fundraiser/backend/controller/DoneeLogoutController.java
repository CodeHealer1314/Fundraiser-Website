package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoneeLogoutController {

    // Step 4: handleLogout(token)
    public boolean handleLogout(String token) {

        // Step 5: invalidateSession(token)
        boolean cleared = Token.invalidateSession(token);

        // If session clearing failed → return false
        if (!cleared) {
            return false;
        }

        // Step 6: return true
        return true;
    }
}