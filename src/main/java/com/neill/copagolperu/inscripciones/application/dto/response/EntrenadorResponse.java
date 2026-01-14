package com.neill.copagolperu.inscripciones.application.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record EntrenadorResponse(
        UUID id,
        String dni,
        String apellidos,
        String nombres,
        String licencia,
        LocalDate fechaNacimiento,
        String telefono,
        String email,
        String fotoUrl,
        String estadoDisciplina,
        String observaciones,
        String nombreAcademia,
        LocalDate fechaRegistro,
        LocalDate fechaActualizacion
) {}