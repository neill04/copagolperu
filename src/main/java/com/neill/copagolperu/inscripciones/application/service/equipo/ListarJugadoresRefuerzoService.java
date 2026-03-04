package com.neill.copagolperu.inscripciones.application.service.equipo;

import com.neill.copagolperu.inscripciones.application.dto.response.JugadorResponse;
import com.neill.copagolperu.inscripciones.application.mapper.JugadorMapper;
import com.neill.copagolperu.inscripciones.domain.model.Categoria;
import com.neill.copagolperu.inscripciones.domain.model.Equipo;
import com.neill.copagolperu.inscripciones.domain.model.Jugador;
import com.neill.copagolperu.inscripciones.domain.repository.EquipoRepository;
import com.neill.copagolperu.inscripciones.domain.repository.JugadorRepository;
import com.neill.copagolperu.inscripciones.infrastructure.repository.RefuerzoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListarJugadoresRefuerzoService {
    private final EquipoRepository equipoRepository;
    private final JugadorRepository jugadorRepository;
    private final RefuerzoRepository refuerzoRepository;

    public List<JugadorResponse> listarJugadoresRefuerzo(UUID equipoId) {
        Equipo equipoActual = equipoRepository.findById(equipoId)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        Categoria categoriaInferior = equipoActual.getCategoria().getCategoriaInferior();

        if (categoriaInferior == null) {
            return Collections.emptyList();
        }

        return equipoRepository.findByAcademiaIdAndCategoria(equipoActual.getAcademia().getId(), categoriaInferior)
                .map(equipoRefuerzo -> {
                    List<Jugador> candidatosPotenciales = jugadorRepository.findByEquipoId(equipoRefuerzo.getId());

                    Set<UUID> idsGlobalesReforzados = refuerzoRepository.findAll()
                            .stream()
                            .map(refuerzo -> refuerzo.getJugador().getId())
                            .collect(Collectors.toSet());

                    return candidatosPotenciales.stream()
                            .filter(jugador -> !idsGlobalesReforzados.contains(jugador.getId()))
                            .map(JugadorMapper::toResponse)
                            .toList();
                })
                .orElse(Collections.emptyList());
    }
}
