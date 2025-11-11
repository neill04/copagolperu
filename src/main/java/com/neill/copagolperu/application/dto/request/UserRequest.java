package com.neill.copagolperu.application.dto.request;

import com.neill.copagolperu.domain.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequest(
        @NotBlank String username,
        @NotBlank String password,
        @NotNull Role role
) {}