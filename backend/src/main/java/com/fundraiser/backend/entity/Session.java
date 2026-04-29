package com.fundraiser.backend.entity;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Data
@Component
@SessionScope
public class Session {

    private String token;
    private boolean active;

    // Empty implementation - will implement in Step 4
    public boolean clearSession() {
        try {
            this.token = null;
            this.active = false;
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
