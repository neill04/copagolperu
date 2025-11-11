package com.neill.copagolperu.application.service.academia.jugador;

import com.neill.copagolperu.application.dto.response.JugadorResponse;
import com.neill.copagolperu.application.mapper.JugadorMapper;
import com.neill.copagolperu.domain.repository.JugadorRepository;
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