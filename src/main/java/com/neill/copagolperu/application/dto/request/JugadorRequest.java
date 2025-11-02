package com.neill.copagolperu.application.dto.request;

import java.time.LocalDate;
import java.util.UUID;

public record JugadorRequest(
        UUID apoderadoId,
        ApoderadoRequest apoderado,
        String dni,
        String apellidos,
        String nombres,
        LocalDate fechaNacimiento,
        String fotoUrl
) {}