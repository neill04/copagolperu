package com.neill.copagolperu.application.mapper;

import com.neill.copagolperu.application.dto.request.EquipoRequest;
import com.neill.copagolperu.application.dto.response.EquipoResponse;
import com.neill.copagolperu.domain.model.Equipo;

import java.util.stream.Collectors;

public class EquipoMapper {

    // de Entidad a EquipoResponse
    public static EquipoResponse toResponse(Equipo equipo) {
        return new EquipoResponse(
                equipo.getId(),
                equipo.getCategoria(),
                equipo.getColorCamiseta(),
                equipo.getFechaRegistro(),
                equipo.getFechaActualizacion(),
                equipo.getActivo(),

                // Datos de la Academia
                equipo.getAcademia().getId(),
                equipo.getAcademia().getNombreAcademia(),
                equipo.getAcademia().getLogoUrl(),

                // Datos del Entrenador
                equipo.getEntrenador().getId(),
                equipo.getEntrenador().getApellidos(),
                equipo.getEntrenador().getNombres(),

                // Datos del Delegado
                equipo.getDelegado().getId(),
                equipo.getDelegado().getApellidos(),
                equipo.getDelegado().getNombres(),

                // Datos de los jugadores
                equipo.getJugadores().stream()
                        .map(j -> new EquipoResponse.DatosJugador(
                                j.getId(),
                                j.getApellidos(),
                                j.getNombres(),
                                j.getDni()
                        ))
                        .collect(Collectors.toList())
        );
    }

    // de EquipoRequest a Entidad
    public static Equipo toEntity(EquipoRequest request) {
        return Equipo.builder()
                .categoria(request.categoria())
                .colorCamiseta(request.colorCamiseta())
                .build();
    }
}