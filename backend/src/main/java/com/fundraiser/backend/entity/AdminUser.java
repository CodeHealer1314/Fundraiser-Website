package com.fundraiser.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    /**
     * Entity handles ALL credential checking
     * Returns true if BOTH email exists AND password matches
     */
    public boolean checkCredentials(String inputEmail, String inputPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // Check email matches AND password matches
        return this.email.equals(inputEmail) &&
                encoder.matches(inputPassword, this.password);
    }
}