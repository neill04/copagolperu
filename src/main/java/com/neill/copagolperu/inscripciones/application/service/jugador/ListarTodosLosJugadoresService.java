package com.neill.copagolperu.inscripciones.application.service.jugador;

import com.neill.copagolperu.inscripciones.application.dto.response.JugadorResponse;
import com.neill.copagolperu.inscripciones.application.mapper.JugadorMapper;
import com.neill.copagolperu.inscripciones.domain.repository.JugadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarTodosLosJugadoresService {
    private final JugadorRepository jugadorRepository;

    public ListarTodosLosJugadoresService(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    public List<JugadorResponse> listarTodosLosJugadores() {
        return jugadorRepository.findAll()
                .stream()
                .map(JugadorMapper::toResponse)
                .toList();
    }
}