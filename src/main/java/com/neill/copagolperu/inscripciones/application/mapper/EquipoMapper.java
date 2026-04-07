package com.neill.copagolperu.inscripciones.application.mapper;

import com.neill.copagolperu.inscripciones.application.dto.request.EquipoRequest;
import com.neill.copagolperu.inscripciones.application.dto.response.EquipoResponse;
import com.neill.copagolperu.inscripciones.domain.model.Equipo;
import com.neill.copagolperu.inscripciones.domain.repository.RefuerzoRepository;
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
                equipo.getEntrenador() != null ? equipo.getEntrenador().getId() : null,
                equipo.getEntrenador() != null ? equipo.getEntrenador().getDni() : null,
                equipo.getEntrenador() != null ? equipo.getEntrenador().getApellidos() : null,
                equipo.getEntrenador() != null ? equipo.getEntrenador().getNombres() : null,
                equipo.getEntrenador() != null ? equipo.getEntrenador().getTelefono() : null,
                equipo.getEntrenador() != null ? equipo.getEntrenador().getFotoUrl() : null,

                // Datos del Delegado
                equipo.getDelegado() != null ? equipo.getDelegado().getId() : null,
                equipo.getDelegado() != null ? equipo.getDelegado().getDni() : null,
                equipo.getDelegado() != null ? equipo.getDelegado().getApellidos() : null,
                equipo.getDelegado() != null ? equipo.getDelegado().getNombres() : null,
                equipo.getDelegado() != null ? equipo.getDelegado().getTelefono() : null,
                equipo.getDelegado() != null ? equipo.getDelegado().getFotoUrl() : null,

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