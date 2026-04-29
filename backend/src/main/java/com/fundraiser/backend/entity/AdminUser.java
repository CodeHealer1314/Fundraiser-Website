package com.fundraiser.backend.entity;

import com.fundraiser.backend.repository.AdminUserRepository;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Data
@Entity
@Table(name = "admin_users")
public class AdminUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    public static AdminUser checkLogin(
            String inputEmail,
            String inputPassword,
            AdminUserRepository repository) {

        // Find user by email from database
        Optional<AdminUser> userOpt = repository.findByEmail(inputEmail);

        // If user not found → return null
        if (userOpt.isEmpty()) {
            return null;
        }

        AdminUser user = userOpt.get();

        // Check password matches
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(inputPassword, user.password)) {
            return null;
        }

        // Both checks passed → return the user
        return user;
    }
}