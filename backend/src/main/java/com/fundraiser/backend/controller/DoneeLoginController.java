package com.fundraiser.backend.controller;

import com.fundraiser.backend.config.JwtUtil;
import com.fundraiser.backend.entity.Donee;
import com.fundraiser.backend.repository.DoneeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoneeLoginController {

    private final DoneeRepository doneeRepository;
    private final JwtUtil jwtUtil;

    // Step 4: login(email, password)
    public String login(String email, String password) {

        // Entity handles finding user AND checking credentials
        Donee donee = Donee.checkLogin(email, password, doneeRepository);

        // If entity returns null → invalid credentials
        if (donee == null) {
            return null;
        }

        // Valid → generate and return JWT token
        return jwtUtil.generateToken(email);
    }
}