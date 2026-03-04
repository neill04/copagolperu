package com.neill.copagolperu.inscripciones.application.service.jugador;

import com.neill.copagolperu.inscripciones.application.dto.response.JugadorResponse;
import com.neill.copagolperu.inscripciones.application.mapper.JugadorMapper;
import com.neill.copagolperu.inscripciones.domain.repository.JugadorRepository;
import com.neill.copagolperu.inscripciones.infrastructure.repository.RefuerzoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class ListarJugadoresPorEquipoService {

    private final JugadorRepository jugadorRepository;
    private final RefuerzoRepository refuerzoRepository;

    public ListarJugadoresPorEquipoService(JugadorRepository jugadorRepository,
                                           RefuerzoRepository refuerzoRepository) {
        this.jugadorRepository = jugadorRepository;
        this.refuerzoRepository = refuerzoRepository;
    }

    @Transactional(readOnly = true)
    public List<JugadorResponse> listarJugadoresPorEquipo(UUID equipoId) {

        List<JugadorResponse> nativos = jugadorRepository.findByEquipoId(equipoId)
                .stream()
                .map(JugadorMapper::toResponse)
                .toList();

        List<JugadorResponse> refuerzos = refuerzoRepository.findByEquipoDestinoId(equipoId)
                .stream()
                .map(refuerzo -> JugadorMapper.toResponse(refuerzo.getJugador()))
                .toList();

        return Stream.concat(nativos.stream(), refuerzos.stream()).toList();
    }
}