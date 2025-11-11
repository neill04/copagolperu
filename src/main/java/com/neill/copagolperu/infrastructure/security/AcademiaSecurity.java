package com.neill.copagolperu.infrastructure.security;

import com.neill.copagolperu.domain.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("academiaSecurity")
public class AcademiaSecurity {
    private final UserRepository userRepository;

    public AcademiaSecurity(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean canAccessAcademy(Authentication authentication, UUID academiaId) {
        if (authentication == null || authentication.getName() == null) return false;

        var userOpt = userRepository.findByUsername(authentication.getName());
        if (userOpt.isEmpty()) return false;

        var user = userOpt.get();

        if (user.getRole().name().equals("ADMINISTRADOR")) {
            return true;
        }

        if (user.getRole().name().equals("ACADEMIA") && user.getAcademia() != null) {
            return user.getAcademia().getId().equals(academiaId);
        }

        return false;
    }
}