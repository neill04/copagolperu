package com.neill.copagolperu.iam.application.dto.request;

import com.neill.copagolperu.iam.domain.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequest(
        @NotBlank String username,
        @NotBlank String password,
        @NotNull Role role
) {}