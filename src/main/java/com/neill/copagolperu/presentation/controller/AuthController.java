package com.neill.copagolperu.presentation.controller;

import com.neill.copagolperu.application.AuthCookieConstants;
import com.neill.copagolperu.application.dto.CreateUserDTO;
import com.neill.copagolperu.application.dto.LoginRequestDTO;
import com.neill.copagolperu.domain.service.AuthService;
import com.neill.copagolperu.infrastructure.configuration.ApiConfig;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConfig.API_BASE_PATH + "/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/check")
    public ResponseEntity<Void> checkAuth(@CookieValue(name = AuthCookieConstants.TOKEN_COOKIE_NAME, required = false) String token) {
        if (token == null || !authService.validateToken(token)) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public void createUser(@RequestBody @Valid CreateUserDTO createUserDTO) {
        authService.createUser(createUserDTO);
    }

    @PostMapping("/login")
    public void login(@RequestBody @Valid LoginRequestDTO loginRequestDTO, HttpServletResponse response) {
        final String token = authService.login(loginRequestDTO);
        final Cookie cookie = createAuthCookie(token);
        response.addCookie(cookie);
    }

    @PostMapping("/logout")
    public void logout(HttpServletResponse response) {
        final Cookie cookie = new Cookie(AuthCookieConstants.TOKEN_COOKIE_NAME, StringUtils.EMPTY);
        /*
        cookie.setHttpOnly(AuthCookieConstants.HTTP_ONLY);
        cookie.setSecure(AuthCookieConstants.COOKIE_SECURE);
        cookie.setPath("/");
        */
        cookie.setMaxAge(0);
        //response.addCookie(cookie);
    }

    private Cookie createAuthCookie(String token) {
        final String SAME_SITE_KEY = "SameSite";
        final Cookie cookie = new Cookie(AuthCookieConstants.TOKEN_COOKIE_NAME, token);
        cookie.setHttpOnly(AuthCookieConstants.HTTP_ONLY);
        cookie.setSecure(AuthCookieConstants.COOKIE_SECURE);
        cookie.setMaxAge(AuthCookieConstants.COOKIE_MAX_AGE);
        cookie.setPath("/");
        cookie.setAttribute(SAME_SITE_KEY, AuthCookieConstants.SAME_SITE);
        return cookie;
    }
}