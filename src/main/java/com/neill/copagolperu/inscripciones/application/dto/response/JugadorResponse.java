package com.neill.copagolperu.inscripciones.application.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record JugadorResponse(
        UUID id,
        String dni,
        String apellidos,
        String nombres,
        LocalDate fechaNacimiento,
        Boolean activo,
        Integer numeroCamiseta,
        String fotoUrl,

        String nombreAcademia,
        UUID equipoId,
        String categoriaEquipo
) {}