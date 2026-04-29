package com.fundraiser.backend.entity;

import com.fundraiser.backend.repository.FundRaiserRepository;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Data
@Entity
@Table(name = "fund_raisers")
public class FundRaiser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    // Step 5: checkLogin(email, password)
    // Entity does the heavy lifting — finds user and validates password
    public static FundRaiser checkLogin(
            String inputEmail,
            String inputPassword,
            FundRaiserRepository repository) {

        // Find fund raiser by email from database
        Optional<FundRaiser> userOpt = repository.findByEmail(inputEmail);

        // Step 6a: user not found → return null
        if (userOpt.isEmpty()) {
            return null;
        }

        FundRaiser user = userOpt.get();

        // Check password matches hashed password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(inputPassword, user.password)) {
            // Step 6a: wrong password → return null
            return null;
        }

        // Step 6: credentials valid → return user
        return user;
    }
}
