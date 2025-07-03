package com.techtracker.user_auth_service.controller;

import com.techtracker.user_auth_service.model.AuthResponse;
import com.techtracker.user_auth_service.model.User;
import com.techtracker.user_auth_service.service.AuthService;
import com.techtracker.user_auth_service.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User createdUser = authService.registerUser(user);
            String token = jwtUtil.generateToken(createdUser.getUsername());
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody User user) {
        User foundUser = authService.authenticate(user.getUsername(), user.getPassword());
        String token = jwtUtil.generateToken(foundUser.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // Stateless logout: client should delete the JWT token
        return ResponseEntity.ok("Logged out successfully. Please delete the token on client side.");
    }
}
