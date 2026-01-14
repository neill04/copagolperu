package com.neill.copagolperu.inscripciones.application.mapper;

import com.neill.copagolperu.inscripciones.application.dto.request.AcademiaRequest;
import com.neill.copagolperu.inscripciones.application.dto.response.AcademiaResponse;
import com.neill.copagolperu.inscripciones.domain.model.Academia;

public class AcademiaMapper {

    private AcademiaMapper() {}

    // de Entidad a AcademiaResponse
    public static AcademiaResponse toResponse(Academia academia) {
        int cantidadEquipos = academia.getEquipos() == null
                ? 0
                : academia.getEquipos().size();

        return new AcademiaResponse(
                academia.getId(),
                academia.getNombreAcademia(),
                academia.getNombreRepresentante(),
                academia.getDniRepresentante(),
                academia.getTelefonoRepresentante(),
                academia.getLiga(),
                academia.getLogoUrl(),
                academia.getActivo(),
                academia.getFechaRegistro(),
                academia.getFechaActualizacion(),
                academia.getDistrito().getNombreDistrito(),
                cantidadEquipos
        );
    }

    // de AcademiaRequest a Entidad
    public static Academia toEntity(AcademiaRequest request) {
        return Academia.builder()
                .nombreAcademia(request.nombreAcademia())
                .nombreRepresentante(request.nombreRepresentante())
                .dniRepresentante(request.dniRepresentante())
                .telefonoRepresentante(request.telefonoRepresentante())
                .liga(request.liga())
                .logoUrl(request.logoUrl())
                .activo(request.activo())
                .build();
    }

}