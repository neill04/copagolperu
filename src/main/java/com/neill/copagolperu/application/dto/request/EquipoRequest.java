package com.neill.copagolperu.application.dto.request;

import com.neill.copagolperu.domain.model.Categoria;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EquipoRequest(
        Categoria categoria,
        String colorCamiseta,
        @NotNull UUID entrenadorId,
        @NotNull UUID delegadoId
) {}