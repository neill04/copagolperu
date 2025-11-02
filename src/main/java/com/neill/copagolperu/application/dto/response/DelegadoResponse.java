package com.neill.copagolperu.application.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record DelegadoResponse(
        UUID id,
        String dni,
        String apellidos,
        String nombres,
        LocalDate fechaNacimiento,
        String telefono,
        String email,
        String fotoUrl,
        LocalDate fechaRegistro,
        LocalDate fechaActualizacion
) {}