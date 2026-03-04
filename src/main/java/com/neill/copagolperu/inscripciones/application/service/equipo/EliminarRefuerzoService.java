package com.neill.copagolperu.inscripciones.application.service.equipo;

import com.neill.copagolperu.inscripciones.infrastructure.repository.RefuerzoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EliminarRefuerzoService {

    private final RefuerzoRepository refuerzoRepository;

    @Transactional
    public void eliminarRefuerzo(UUID equipoId, UUID jugadorId) {
        if (!refuerzoRepository.existsByJugadorId(jugadorId)) {
            throw new IllegalArgumentException("El jugador no existe");
        }

        refuerzoRepository.deleteByJugadorIdAndEquipoDestinoId(jugadorId, equipoId);
    }
}
