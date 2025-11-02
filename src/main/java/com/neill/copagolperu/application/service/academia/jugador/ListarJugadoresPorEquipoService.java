package com.neill.copagolperu.application.service.academia.jugador;

import com.neill.copagolperu.application.dto.response.JugadorResponse;
import com.neill.copagolperu.application.mapper.JugadorMapper;
import com.neill.copagolperu.domain.repository.JugadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListarJugadoresPorEquipoService {

    private final JugadorRepository jugadorRepository;

    public ListarJugadoresPorEquipoService(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    public List<JugadorResponse> listarJugadoresPorEquipo(UUID equipoId) {
        return jugadorRepository.findByEquipoId(equipoId)
                .stream()
                .map(JugadorMapper::toResponse)
                .toList();
    }
}