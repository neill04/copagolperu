package com.neill.copagolperu.inscripciones.application.service.equipo;

import com.neill.copagolperu.inscripciones.domain.repository.EquipoRepository;
import com.neill.copagolperu.inscripciones.domain.repository.RefuerzoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EliminarEquipoService {
    private final EquipoRepository equipoRepository;
    private final RefuerzoRepository refuerzoRepository;

    @Transactional
    public void eliminarEquipo(UUID equipoId) {
        refuerzoRepository.deleteByJugadorEquipoId(equipoId);

        equipoRepository.deleteById(equipoId);
    }
}
