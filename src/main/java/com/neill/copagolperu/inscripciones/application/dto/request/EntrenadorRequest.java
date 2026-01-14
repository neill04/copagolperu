package com.neill.copagolperu.inscripciones.application.dto.request;

import java.time.LocalDate;

public record EntrenadorRequest(
        String dni,
        String apellidos,
        String nombres,
        String licencia,
        LocalDate fechaNacimiento,
        String telefono,
        String email,
        String fotoUrl,
        String estadoDisciplina,
        String observaciones
) {}