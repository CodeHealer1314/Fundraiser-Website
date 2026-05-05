package com.fundraiser.backend.controller;

import com.fundraiser.backend.config.JwtUtil;
import com.fundraiser.backend.entity.PlatformManager;
import com.fundraiser.backend.repository.PlatformManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlatformManagerLoginController {

    private final PlatformManagerRepository platformManagerRepository;
    private final JwtUtil jwtUtil;

    // Step 4: login(email, password)
    public String login(String email, String password) {

        // Entity handles finding user AND checking credentials
        PlatformManager manager = PlatformManager.checkLogin(
                email, password, platformManagerRepository);

        // If entity returns null → invalid credentials
        if (manager == null) {
            return null;
        }

        // Valid → generate and return JWT token
        return jwtUtil.generateToken(email);
    }
}