package com.techtracker.user_auth_service.model;

import lombok.*;

@Getter
@Setter
public class AuthResponse {
    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }
}
