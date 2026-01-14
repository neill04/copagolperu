package com.neill.copagolperu.iam.domain.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMINISTRADOR,
    ACADEMIA;

    @Override
    public String getAuthority() {
        return name();
    }
}