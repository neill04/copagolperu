package com.neill.copagolperu.iam.application.service;

import com.neill.copagolperu.iam.application.dto.request.LoginRequest;
import com.neill.copagolperu.iam.application.dto.request.UserRequest;
import com.neill.copagolperu.iam.application.dto.response.LoginResponse;
import com.neill.copagolperu.iam.domain.model.User;

import java.util.UUID;

public interface AuthService {
    void createUser(UserRequest request);
    User getUser(UUID id);
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse getUserInfo(String username);
    boolean validateToken(String token);
    String getUserFromToken(String token);
    User getUserByUsername(String username);
}