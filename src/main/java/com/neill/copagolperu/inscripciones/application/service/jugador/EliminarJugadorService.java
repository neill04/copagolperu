package com.neill.copagolperu.inscripciones.application.service.jugador;

import com.neill.copagolperu.inscripciones.domain.repository.JugadorRepository;
import com.neill.copagolperu.inscripciones.domain.repository.RefuerzoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EliminarJugadorService {
    private final JugadorRepository jugadorRepository;
    private final RefuerzoRepository refuerzoRepository;

    @Transactional
    public void eliminarJugador(UUID jugadorId){
        if (!jugadorRepository.existsById(jugadorId)) {
            throw new RuntimeException("El jugador no existe o ya fue eliminado.");
        }
        refuerzoRepository.deleteByJugadorId(jugadorId);

        jugadorRepository.deleteById(jugadorId);
    }
}