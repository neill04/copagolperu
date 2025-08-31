package com.neill.copagolperu.domain.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMINISTRADOR,
    ACADEMIA;

    @Override
    public String getAuthority() {
        return name();
    }
}