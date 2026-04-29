package com.fundraiser.backend.controller;

import com.fundraiser.backend.config.JwtUtil;
import com.fundraiser.backend.entity.FundRaiser;
import com.fundraiser.backend.repository.FundRaiserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginController {

    private final FundRaiserRepository fundRaiserRepository;
    private final JwtUtil jwtUtil;

    // Step 4: login(email, password)
    public String login(String email, String password) {

        // Step 5: checkLogin(email, password) — entity does the work
        FundRaiser user = FundRaiser.checkLogin(email, password, fundRaiserRepository);

        // Step 7a: returns null → invalid credentials
        if (user == null) {
            return null;
        }

        // Step 7: createAccessToken → Step 8: return string token
        return jwtUtil.generateToken(email);
    }
}
