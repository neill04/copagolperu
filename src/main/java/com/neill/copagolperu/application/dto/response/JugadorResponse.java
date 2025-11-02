package com.neill.copagolperu.application.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record JugadorResponse(
        UUID id,
        String dni,
        String apellidos,
        String nombres,
        LocalDate fechaNacimiento,
        String fotoUrl,
        Boolean activo,

        UUID equipoId,

        UUID apoderadoId,
        String dniApoderado,
        String apellidosApoderado,
        String nombresApoderado
) {}