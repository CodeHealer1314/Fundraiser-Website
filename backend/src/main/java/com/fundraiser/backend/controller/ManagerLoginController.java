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

    // BCE method: login(String email, String password)
    // Returns JWT token if valid, null if invalid
    // Maps to sequence diagram step 4
    public String login(String email, String password) {

        // Step 5: checkLogin(email, password)
        Optional<AdminUser> userOpt = adminUserRepository.findByEmail(email);

        // Step 6a: user not found → return null
        if (userOpt.isEmpty()) {
            return null;
        }

        AdminUser user = userOpt.get();

        // Step 6: validate password
        if (!user.checkLogin(password)) {
            return null;
        }

        // Step 7: createAccessToken(email) → Step 8: return token
        return jwtUtil.generateToken(email);
    }
}