package com.techtracker.user_auth_service.controller;

import com.techtracker.user_auth_service.model.AuthResponse;
import com.techtracker.user_auth_service.model.User;
import com.techtracker.user_auth_service.service.AuthService;
import com.techtracker.user_auth_service.util.JwtUtil;
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
        User createdUser = authService.registerUser(user);
        String token = jwtUtil.generateToken(createdUser.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody User user) {
        User foundUser = authService.authenticate(user.getUsername(), user.getPassword());
        String token = jwtUtil.generateToken(foundUser.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
