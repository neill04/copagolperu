package com.neill.copagolperu.application.mapper;

import com.neill.copagolperu.application.dto.request.AcademiaRequest;
import com.neill.copagolperu.application.dto.response.AcademiaResponse;
import com.neill.copagolperu.domain.model.Academia;

public class AcademiaMapper {

    // de Entidad a AcademiaResponse
    public static AcademiaResponse toResponse(Academia academia) {
        return new AcademiaResponse(
                academia.getId(),
                academia.getNombreAcademia(),
                academia.getNombreRepresentante(),
                academia.getDniRepresentante(),
                academia.getTelefonoRepresentante(),
                academia.getLogoUrl(),
                academia.getActivo(),
                academia.getFechaRegistro(),
                academia.getFechaActualizacion(),
                academia.getDistrito().getNombreDistrito()
        );
    }

    // de AcademiaRequest a Entidad
    public static Academia toEntity(AcademiaRequest request) {
        return Academia.builder()
                .nombreAcademia(request.nombreAcademia())
                .nombreRepresentante(request.nombreRepresentante())
                .dniRepresentante(request.dniRepresentante())
                .telefonoRepresentante(request.telefonoRepresentante())
                .logoUrl(request.logoUrl())
                .build();
    }

}