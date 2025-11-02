package com.neill.copagolperu.application.dto.request;

import java.time.LocalDate;

public record DelegadoRequest(
        String dni,
        String apellidos,
        String nombres,
        LocalDate fechaNacimiento,
        String telefono,
        String email,
        String fotoUrl
) {}