package com.neill.copagolperu.application.dto;

import java.util.UUID;

public record UserInfoDTO(
        UUID id,
        String username,
        String role,
        Boolean activo,
        UUID academiaId,
        String logoUrl,
        String nombreAcademia
) {}