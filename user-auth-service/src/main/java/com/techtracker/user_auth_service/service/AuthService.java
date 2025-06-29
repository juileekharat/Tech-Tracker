package com.techtracker.user_auth_service.service;

import com.techtracker.user_auth_service.model.User;

public interface AuthService {
    User registerUser(User user);
    User authenticate(String username, String password);
}
