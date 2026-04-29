package com.fundraiser.backend.controller;

import com.fundraiser.backend.config.JwtUtil;
import com.fundraiser.backend.entity.AdminUser;
import com.fundraiser.backend.repository.AdminUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerLoginController {

    private final AdminUserRepository adminUserRepository;
    private final JwtUtil jwtUtil;

    /**
     * Asks entity to check credentials
     * Entity handles all the checking logic
     */
    public String login(String email, String password) {

        // Find user by email from database
        Optional<AdminUser> userOpt = adminUserRepository.findByEmail(email);

        // If user not found → return null
        if (userOpt.isEmpty()) {
            return null;
        }

        AdminUser user = userOpt.get();

        // Entity checks BOTH email and password match
        // Controller doesn't need to know HOW it checks
        if (!user.checkCredentials(email, password)) {
            return null;
        }

        // Generate and return JWT token
        return jwtUtil.generateToken(email);
    }
}