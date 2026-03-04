package com.neill.copagolperu.inscripciones.application.mapper;

import com.neill.copagolperu.inscripciones.application.dto.request.EquipoRequest;
import com.neill.copagolperu.inscripciones.application.dto.response.EquipoResponse;
import com.neill.copagolperu.inscripciones.domain.model.Equipo;
import com.neill.copagolperu.inscripciones.infrastructure.repository.RefuerzoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EquipoMapper {

    private final RefuerzoRepository refuerzoRepository;

    // de Entidad a EquipoResponse
    public EquipoResponse toResponse(Equipo equipo) {
        var refuerzos = refuerzoRepository.findByEquipoDestinoId(equipo.getId());

        List<EquipoResponse.DatosJugador> todosJugadores = new ArrayList<>();

        equipo.getJugadores().forEach(jugador ->
            todosJugadores.add(new EquipoResponse.DatosJugador(jugador.getId(), jugador.getApellidos(), jugador.getNombres(), jugador.getDni()))
        );

        refuerzos.forEach(refuerzo -> {
            var j = refuerzo.getJugador();
            todosJugadores.add(new EquipoResponse.DatosJugador(j.getId(), j.getApellidos(), j.getNombres(), j.getDni()));
        });

        return new EquipoResponse(
                equipo.getId(),
                equipo.getCategoria(),
                equipo.getColorCamiseta(),
                todosJugadores.size(),
                equipo.getFechaRegistro(),
                equipo.getFechaActualizacion(),
                equipo.getActivo(),

                // Datos de la Academia
                equipo.getAcademia().getId(),
                equipo.getAcademia().getNombreAcademia(),
                equipo.getAcademia().getLogoUrl(),

                // Datos del Entrenador
                equipo.getEntrenador().getId(),
                equipo.getEntrenador().getDni(),
                equipo.getEntrenador().getApellidos(),
                equipo.getEntrenador().getNombres(),
                equipo.getEntrenador().getTelefono(),
                equipo.getEntrenador().getFotoUrl(),

                // Datos del Delegado
                equipo.getDelegado().getId(),
                equipo.getDelegado().getDni(),
                equipo.getDelegado().getApellidos(),
                equipo.getDelegado().getNombres(),
                equipo.getDelegado().getTelefono(),
                equipo.getDelegado().getFotoUrl(),

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
    public Equipo toEntity(EquipoRequest request) {
        return Equipo.builder()
                .categoria(request.categoria())
                .colorCamiseta(request.colorCamiseta())
                .build();
    }
}