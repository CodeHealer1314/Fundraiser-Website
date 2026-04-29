package com.fundraiser.backend.controller;

import com.fundraiser.backend.entity.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutController {

    private final Session session;

    // Empty implementation - will implement in Step 4
    public boolean handleLogout() {
        return session.clearSession();
    }
}
