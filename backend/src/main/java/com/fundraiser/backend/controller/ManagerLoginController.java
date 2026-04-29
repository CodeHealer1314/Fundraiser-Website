package com.fundraiser.backend.controller;

import com.fundraiser.backend.config.JwtUtil;
import com.fundraiser.backend.entity.AdminUser;
import com.fundraiser.backend.repository.AdminUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerLoginController {

    private final AdminUserRepository adminUserRepository;
    private final JwtUtil jwtUtil;

    public String login(String email, String password) {

        // Entity handles finding user AND checking credentials
        AdminUser user = AdminUser.checkLogin(email, password, adminUserRepository);

        // If entity returns null → invalid credentials
        if (user == null) {
            return null;
        }

        // Valid → generate and return JWT token
        return jwtUtil.generateToken(email);
    }
}