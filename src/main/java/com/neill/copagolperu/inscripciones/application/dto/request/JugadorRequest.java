package com.neill.copagolperu.inscripciones.application.dto.request;

import java.time.LocalDate;

public record JugadorRequest(
        String dni,
        String apellidos,
        String nombres,
        LocalDate fechaNacimiento,
        Integer numeroCamiseta,
        String fotoUrl
) {}