package com.neill.copagolperu.inscripciones.application.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record  AcademiaResponse(
        UUID id,
        String nombreAcademia,
        String nombreRepresentante,
        String dniRepresentante,
        String telefonoRepresentante,
        String liga,
        String logoUrl,
        Boolean activo,
        LocalDate fechaRegistro,
        LocalDate fechaActualizacion,
        String nombreDistrito,
        int totalEquipos
) {}