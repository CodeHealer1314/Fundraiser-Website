package com.fundraiser.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Token {

    private String value;

    // Step 5: invalidateSession(token)
    public static boolean invalidateSession(String token) {

        // If token is null or empty → return false
        if (token == null || token.isEmpty()) {
            return false;
        }

        // Token is valid → session cleared successfully
        return true;
    }
}
