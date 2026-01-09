package com.neill.copagolperu.presentation.controller;

import com.neill.copagolperu.application.dto.UserInfoDTO;
import com.neill.copagolperu.application.dto.request.AsignarAcademiaRequest;
import com.neill.copagolperu.application.exception.UserNotFoundException;
import com.neill.copagolperu.application.mapper.UserMapper;
import com.neill.copagolperu.application.service.admin.UserAcademiaService;
import com.neill.copagolperu.domain.model.User;
import com.neill.copagolperu.domain.repository.UserRepository;
import com.neill.copagolperu.domain.service.AuthService;
import com.neill.copagolperu.infrastructure.configuration.ApiConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(ApiConfig.API_BASE_PATH + "/admin/users")
@PreAuthorize("hasAuthority('ADMINISTRADOR')")
public class AdminUserController {
    private final UserAcademiaService userAcademiaService;
    private final UserRepository userRepository;
    private final AuthService authService;

    public AdminUserController(UserAcademiaService userAcademiaService,
                               UserRepository userRepository,
                               AuthService authService) {
        this.userAcademiaService = userAcademiaService;
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @PostMapping("/{userId}/academia")
    public ResponseEntity<UserInfoDTO> asignarAcademia(@PathVariable UUID userId, @RequestBody AsignarAcademiaRequest request) {
        User user = userAcademiaService.asignarAcademiaAUsuario(userId, request.academiaId());
        return ResponseEntity.ok(UserMapper.toUserInfo(user));
    }

    @DeleteMapping("/{userId}/academia")
    public ResponseEntity<UserInfoDTO> removerAcademia(@PathVariable UUID userId) {
        User user = userAcademiaService.removerAcademiaDeUsuario(userId);
        return ResponseEntity.ok(UserMapper.toUserInfo(user));
    }

    @PatchMapping("/{userId}/toggle-status")
    public ResponseEntity<Map<String, Object>> toggleUserStatus(@PathVariable UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

        user.setActivo(!user.getActivo());
        userRepository.save(user);

        String mensaje = user.getActivo()
                ? "Usuario activado exitosamente"
                : "Usuario inactivado exitosamente";

        return ResponseEntity.ok(Map.of(
                "mensaje", mensaje,
                "activo", user.getActivo(),
                "username", user.getUsername()
        ));
    }
}