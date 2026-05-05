package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlatformManagerLogoutController {

    // Step 4: handleLogout()
    public boolean handleLogout() {

        // Step 5: clearSession()
        boolean cleared = Session.clearSession();

        // If session clearing failed → return false
        if (!cleared) {
            return false;
        }

        // Step 6: return true
        return true;
    }
}