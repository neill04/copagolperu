package com.neill.copagolperu.inscripciones.application.dto.request;

public record AcademiaRequest(
        String nombreAcademia,
        String nombreRepresentante,
        String dniRepresentante,
        String telefonoRepresentante,
        String liga,
        String logoUrl,
        Boolean activo,
        Long distritoId
) {}