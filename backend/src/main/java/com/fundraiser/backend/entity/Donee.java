package com.fundraiser.backend.entity;

import com.fundraiser.backend.repository.DoneeRepository;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Data
@Entity
@Table(name = "donees")
public class Donee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    // Step 4: real implementation of checkLogin
    public static Donee checkLogin(
            String inputEmail,
            String inputPassword,
            DoneeRepository repository) {

        // Find user by email from database
        Optional<Donee> doneeOpt = repository.findByEmail(inputEmail);

        // If user not found → return null
        if (doneeOpt.isEmpty()) {
            return null;
        }

        Donee donee = doneeOpt.get();

        // Check password matches
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(inputPassword, donee.password)) {
            return null;
        }

        // Both checks passed → return the donee
        return donee;
    }
}