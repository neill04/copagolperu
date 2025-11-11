package com.neill.copagolperu.application.mapper;

import com.neill.copagolperu.application.dto.request.JugadorRequest;
import com.neill.copagolperu.application.dto.response.JugadorResponse;
import com.neill.copagolperu.domain.model.Jugador;

public class JugadorMapper {

    // de Entidad a JugadorResponse
    public static JugadorResponse toResponse(Jugador jugador) {
        return new JugadorResponse(
                jugador.getId(),
                jugador.getDni(),
                jugador.getApellidos(),
                jugador.getNombres(),
                jugador.getFechaNacimiento(),
                jugador.getFotoUrl(),
                jugador.getActivo(),
                jugador.getEquipo().getAcademia().getNombreAcademia(),
                jugador.getEquipo().getId(),
                jugador.getEquipo().getCategoria().name(),
                jugador.getApoderado().getId(),
                jugador.getApoderado().getDni(),
                jugador.getApoderado().getApellidos(),
                jugador.getApoderado().getNombres()
        );
    }

    // de JugadorRequest a Entidad
    public static Jugador toEntity(JugadorRequest request) {
        return Jugador.builder()
                .dni(request.dni())
                .apellidos(request.apellidos())
                .nombres(request.nombres())
                .fechaNacimiento(request.fechaNacimiento())
                .fotoUrl(request.fotoUrl())
                .build();
    }
}