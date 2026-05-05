package com.fundraiser.backend.entity;

import lombok.Data;

@Data
public class Session {

    private String sessionId;

    // Step 5: clearSession()
    public static boolean clearSession() {
        // Session cleared successfully
        return true;
    }
}