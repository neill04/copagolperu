package com.neill.copagolperu.inscripciones.application.service.jugador;

import com.neill.copagolperu.inscripciones.application.dto.response.JugadorResponse;
import com.neill.copagolperu.inscripciones.application.mapper.JugadorMapper;
import com.neill.copagolperu.inscripciones.domain.repository.JugadorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BuscarJugadorService {

    private final JugadorRepository jugadorRepository;

    public BuscarJugadorService(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    public Optional<JugadorResponse> buscarJugador(UUID id) {
        return jugadorRepository.findById(id)
                .map(JugadorMapper::toResponse);
    }
}