package com.fundraiser.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "user_accounts")
public class UserAccount {

    @Id
    private String accountId;

    private LocalDateTime createdAt;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String role;

    private boolean suspended = false;

    // Auto generate accountId and createdAt
    public void refreshAuto() {
        this.accountId = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
    }
}
