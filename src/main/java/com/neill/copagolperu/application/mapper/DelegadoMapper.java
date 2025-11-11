package com.neill.copagolperu.application.mapper;

import com.neill.copagolperu.application.dto.request.DelegadoRequest;
import com.neill.copagolperu.application.dto.response.DelegadoResponse;
import com.neill.copagolperu.domain.model.Delegado;

public class DelegadoMapper {

    // de Entidad a DelegadoResponse
    public static DelegadoResponse toResponse(Delegado delegado) {
        return new DelegadoResponse(
                delegado.getId(),
                delegado.getDni(),
                delegado.getApellidos(),
                delegado.getNombres(),
                delegado.getFechaNacimiento(),
                delegado.getTelefono(),
                delegado.getEmail(),
                delegado.getFotoUrl(),
                delegado.getAcademia().getNombreAcademia(),
                delegado.getFechaRegistro(),
                delegado.getFechaActualizacion()
        );
    }

    // de DelegadoRequest a Entidad
    public static Delegado toEntity(DelegadoRequest request) {
        return Delegado.builder()
                .dni(request.dni())
                .apellidos(request.apellidos())
                .nombres(request.nombres())
                .fechaNacimiento(request.fechaNacimiento())
                .telefono(request.telefono())
                .email(request.email())
                .fotoUrl(request.fotoUrl())
                .build();
    }
}