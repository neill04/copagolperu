package com.neill.copagolperu.application.dto;

import com.neill.copagolperu.domain.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public final class CreateUserDTO {
    @Email
    @NotBlank
    private final String email;

    @NotBlank
    private final String firstName;

    @NotBlank
    private final String password;

    @NotNull
    private final Role role;

    public CreateUserDTO(String email, String firstName, String password, Role role) {
        this.email = email;
        this.firstName = firstName;
        this.password = password;
        this.role = role;
    }

    public String email() {
        return email;
    }

    public String firstName() {
        return firstName;
    }

    public String password() {
        return password;
    }

    public Role role() {
        return role;
    }
}