package com.fundraiser.backend.boundary;

import com.fundraiser.backend.controller.LoginController;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/fundraiser")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class LoginPage {

    private final LoginController loginController;

    // Empty implementation - will implement in Step 4
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return null;
    }

    @Data
    public static class LoginRequest {
        private String email;
        private String password;
    }
}