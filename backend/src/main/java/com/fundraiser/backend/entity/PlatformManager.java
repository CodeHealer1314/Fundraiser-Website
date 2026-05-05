package com.fundraiser.backend.entity;

import com.fundraiser.backend.repository.PlatformManagerRepository;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Data
@Entity
@Table(name = "platform_managers")
public class PlatformManager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    // Step 4: real implementation of checkLogin
    public static PlatformManager checkLogin(
            String inputEmail,
            String inputPassword,
            PlatformManagerRepository repository) {

        // Find user by email from database
        Optional<PlatformManager> managerOpt =
                repository.findByEmail(inputEmail);

        // If user not found → return null
        if (managerOpt.isEmpty()) {
            return null;
        }

        PlatformManager manager = managerOpt.get();

        // Check password matches
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(inputPassword, manager.password)) {
            return null;
        }

        // Both checks passed → return the manager
        return manager;
    }
}