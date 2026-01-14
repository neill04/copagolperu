package com.neill.copagolperu.inscripciones.application.dto.request;

import com.neill.copagolperu.inscripciones.domain.model.Categoria;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EquipoRequest(
        Categoria categoria,
        String colorCamiseta,
        @NotNull UUID entrenadorId,
        @NotNull UUID delegadoId
) {}