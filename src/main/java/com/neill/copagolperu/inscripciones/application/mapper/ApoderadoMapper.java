/*
package com.neill.copagolperu.application.mapper;

import com.neill.copagolperu.inscripciones.application.dto.request.ApoderadoRequest;
import com.neill.copagolperu.inscripciones.application.dto.response.ApoderadoResponse;
import com.neill.copagolperu.inscripciones.application.dto.response.EquipoResponse;
import com.neill.copagolperu.domain.model.Apoderado;

import java.util.stream.Collectors;

public class ApoderadoMapper {

    // de Entidad a ApoderadoResponse
    public static ApoderadoResponse toResponse(Apoderado apoderado) {
        return new ApoderadoResponse(
                apoderado.getId(),
                apoderado.getDni(),
                apoderado.getApellidos(),
                apoderado.getNombres(),
                apoderado.getFechaNacimiento(),
                apoderado.getParentesco(),
                apoderado.getTelefono(),

                apoderado.getJugadores().stream()
                        .map(j -> new EquipoResponse.DatosJugador(
                                j.getId(),
                                j.getApellidos(),
                                j.getNombres(),
                                j.getDni()
                        ))
                        .collect(Collectors.toList())
        );
    }

    // de ApoderadoRequest a Entidad
    public static Apoderado toEntity(ApoderadoRequest request) {
        return Apoderado.builder()
                .dni(request.dni())
                .apellidos(request.apellidos())
                .nombres(request.nombres())
                .fechaNacimiento(request.fechaNacimiento())
                .parentesco(request.parentesco())
                .telefono(request.telefono())
                .build();
    }
}
*/