package com.neill.copagolperu.application.mapper;

import com.neill.copagolperu.application.dto.UserInfoDTO;
import com.neill.copagolperu.application.dto.request.LoginRequest;
import com.neill.copagolperu.application.dto.request.UserRequest;
import com.neill.copagolperu.domain.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class AuthMapper {
    private AuthMapper() {
        throw new UnsupportedOperationException("This class should never be instantiated");
    }

    public static User fromDTO(final UserRequest request) {
        return User.builder()
                .username(request.username())
                .password(request.password())
                .role(request.role())
                .build();
    }

    public static Authentication fromDTO(final LoginRequest loginRequest) {
        return new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());
    }

    public static UserInfoDTO toDTO(final User user) {
        return new UserInfoDTO(
                user.getId(),
                user.getUsername(),
                user.getRole().name(),
                user.getAcademia() != null ? user.getAcademia().getId() : null,
                user.getAcademia() != null ? user.getAcademia().getNombreAcademia() : null
        );
    }
}