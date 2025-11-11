package com.neill.copagolperu.domain.service;

import com.neill.copagolperu.application.dto.UserInfoDTO;
import com.neill.copagolperu.application.dto.request.LoginRequest;
import com.neill.copagolperu.application.dto.request.UserRequest;
import com.neill.copagolperu.application.dto.response.LoginResponse;
import com.neill.copagolperu.domain.model.User;

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