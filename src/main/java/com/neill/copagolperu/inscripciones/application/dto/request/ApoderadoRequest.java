package com.neill.copagolperu.inscripciones.application.dto.request;

import java.time.LocalDate;

public record ApoderadoRequest(
        String dni,
        String apellidos,
        String nombres,
        LocalDate fechaNacimiento,
        String parentesco,
        String telefono
) {}