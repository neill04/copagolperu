package com.neill.copagolperu.inscripciones.application.service.jugador;

import com.neill.copagolperu.inscripciones.application.dto.response.JugadorResponse;
import com.neill.copagolperu.inscripciones.application.mapper.JugadorMapper;
import com.neill.copagolperu.inscripciones.domain.repository.JugadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListarTodosLosJugadoresPorAcademiaService {

    private final JugadorRepository jugadorRepository;

    public ListarTodosLosJugadoresPorAcademiaService(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    public List<JugadorResponse> listarTodosLosJugadoresPorAcademia(UUID academiaId) {
        return jugadorRepository.findByEquipo_Academia_Id(academiaId)
                .stream()
                .map(JugadorMapper::toResponse)
                .toList();
    }
}