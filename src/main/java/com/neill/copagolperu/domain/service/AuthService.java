package com.neill.copagolperu.domain.service;

import com.neill.copagolperu.application.dto.CreateUserDTO;
import com.neill.copagolperu.application.dto.LoginRequestDTO;
import com.neill.copagolperu.domain.model.User;

import java.util.UUID;

public interface AuthService {
    String login(LoginRequestDTO loginRequestDTO);
    boolean validateToken(String token);
    String getUserFromToken(String token);
    void createUser(CreateUserDTO createUserDTO);
    User getUser(UUID id);
}