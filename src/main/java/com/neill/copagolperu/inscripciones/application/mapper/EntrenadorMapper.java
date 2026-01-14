package com.neill.copagolperu.inscripciones.application.mapper;

import com.neill.copagolperu.inscripciones.application.dto.request.EntrenadorRequest;
import com.neill.copagolperu.inscripciones.application.dto.response.EntrenadorResponse;
import com.neill.copagolperu.inscripciones.domain.model.Entrenador;

public class EntrenadorMapper {

    private EntrenadorMapper() {}

    // de Entidad a EntrenadorResponse
    public static EntrenadorResponse toResponse(Entrenador entrenador) {
        return new EntrenadorResponse(
                entrenador.getId(),
                entrenador.getDni(),
                entrenador.getApellidos(),
                entrenador.getNombres(),
                entrenador.getLicencia(),
                entrenador.getFechaNacimiento(),
                entrenador.getTelefono(),
                entrenador.getEmail(),
                entrenador.getFotoUrl(),
                entrenador.getEstadoDisciplina(),
                entrenador.getObservaciones(),
                entrenador.getAcademia().getNombreAcademia(),
                entrenador.getFechaRegistro(),
                entrenador.getFechaActualizacion()
        );
    }

    // de EntrenadorRequest a Entidad
    public static Entrenador toEntity(EntrenadorRequest request) {
        return Entrenador.builder()
                .dni(request.dni())
                .apellidos(request.apellidos())
                .nombres(request.nombres())
                .licencia(request.licencia())
                .fechaNacimiento(request.fechaNacimiento())
                .telefono(request.telefono())
                .email(request.email())
                .fotoUrl(request.fotoUrl())
                .estadoDisciplina(request.estadoDisciplina())
                .observaciones(request.observaciones())
                .build();
    }
}