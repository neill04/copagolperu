package com.neill.copagolperu.domain.service;

import org.springframework.security.core.Authentication;

public interface TokenService_ {
    String generateToken(Authentication authentication);
    String getUserFromToken(String token);
    boolean validateToken(String token);
}