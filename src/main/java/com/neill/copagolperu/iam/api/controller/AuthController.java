package com.neill.copagolperu.presentation.controller;

import com.neill.copagolperu.application.dto.request.LoginRequest;
import com.neill.copagolperu.application.dto.request.UserRequest;
import com.neill.copagolperu.application.dto.response.LoginResponse;
import com.neill.copagolperu.domain.model.User;
import com.neill.copagolperu.domain.service.AuthService;
import com.neill.copagolperu.shared.infrastructure.config.ApiConfig;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(ApiConfig.API_BASE_PATH + "/auth")
public class AuthController {

    private static final Logger logger = LogManager.getLogger(AuthController.class);
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Verifica si el usuario está autenticado
     * GET /api/auth/check
     * Header: Authorization: Bearer {token}
     */
    @GetMapping("/check")
    public ResponseEntity<Map<String, Object>> checkAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()
                || authentication.getPrincipal().equals("anonymousUser")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("authenticated", false));
        }

        User user = (User) authentication.getPrincipal();

        return ResponseEntity.ok(Map.of(
                "authenticated", true,
                "username", user.getUsername(),
                "role", user.getRole().name()
        ));
    }

    /**
     * Registra un nuevo usuario
     * POST /api/auth/register
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> createUser(@RequestBody @Valid UserRequest userRequest) {
        try {
            authService.createUser(userRequest);
            logger.info("[AUTH] User registered successfully: {}", userRequest.username());

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("message", "Usuario registrado exitosamente"));
        } catch (Exception e) {
            logger.error("[AUTH] Error registering user", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Login - Retorna el token JWT y la información del usuario
     * POST /api/auth/login
     *
     * Body:
     * {
     *   "username": "admin.master",
     *   "password": "password123"
     * }
     *
     * Response:
     * {
     *   "token": "eyJhbGciOiJIUzI1NiJ9...",
     *   "type": "Bearer",
     *   "expiresIn": 7200,
     *   "user": {
     *     "id": "...",
     *     "username": "admin.master",
     *     "role": "ADMINISTRADOR",
     *     ...
     *   }
     * }
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        try {
            logger.info("[AUTH] Login attempt for user: {}", loginRequest.username());

            LoginResponse response = authService.login(loginRequest);

            logger.info("[AUTH] Login successful for user: {}", loginRequest.username());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.error("[AUTH] Login failed for user: {}", loginRequest.username(), e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    /**
     * Logout - Solo retorna confirmación
     * POST /api/auth/logout
     *
     * El frontend debe eliminar el token del localStorage
     */
    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout() {
        logger.info("[AUTH] Logout request received");

        return ResponseEntity.ok(Map.of(
                "message", "Logout exitoso. Elimina el token del almacenamiento local."
        ));
    }

    /**
     * Obtiene información del usuario actual
     * GET /api/auth/me
     * Header: Authorization: Bearer {token}
     */
    @GetMapping("/me")
    public ResponseEntity<LoginResponse> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()
                || authentication.getPrincipal().equals("anonymousUser")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = (User) authentication.getPrincipal();
        LoginResponse response = authService.getUserInfo(user.getUsername());

        return ResponseEntity.ok(response);
    }
}