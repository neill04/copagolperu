package com.neill.copagolperu.application.dto.request;

import java.time.LocalDate;

public record JugadorRequest(
        String dni,
        String apellidos,
        String nombres,
        LocalDate fechaNacimiento,
        Integer numeroCamiseta,
        String fotoUrl
) {}