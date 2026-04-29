package com.fundraiser.backend.entity;

import com.fundraiser.backend.repository.UserProfileRepository;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    private String userId;

    private LocalDateTime createdAt;

    private String fullname;

    private String username;

    private String email;

    private String role;

    private boolean suspended = false;

    // Empty implementation - will implement in Step 4
    public static UserProfile saveProfile(
            Object data,
            UserProfileRepository repository) {
        return null; // placeholder
    }

    // Auto generate userId and createdAt
    public void refreshAuto() {
        this.userId = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
    }
}
