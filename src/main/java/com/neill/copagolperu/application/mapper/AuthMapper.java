package com.neill.copagolperu.application.mapper;

import com.neill.copagolperu.application.dto.CreateUserDTO;
import com.neill.copagolperu.application.dto.LoginRequestDTO;
import com.neill.copagolperu.application.dto.UserResponseDTO;
import com.neill.copagolperu.domain.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class AuthMapper {
    private AuthMapper() {
        throw new UnsupportedOperationException("This class should never be instantiated");
    }

    public static User fromDTO(final CreateUserDTO createUserDTO) {
        return User.builder()
                .email(createUserDTO.email())
                .firstName(createUserDTO.firstName())
                .role(createUserDTO.role())
                .build();
    }

    public static Authentication fromDTO(final LoginRequestDTO loginRequestDTO) {
        return new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
    }

    public static UserResponseDTO toDTO(final User user) {
        return new UserResponseDTO(user.getId(), user.getFirstName(), user.getEmail(), user.getRole());
    }
}