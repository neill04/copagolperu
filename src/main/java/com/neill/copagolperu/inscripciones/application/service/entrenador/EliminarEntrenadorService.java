package com.neill.copagolperu.inscripciones.application.service.entrenador;

import com.neill.copagolperu.inscripciones.domain.model.Equipo;
import com.neill.copagolperu.inscripciones.domain.repository.EntrenadorRepository;
import com.neill.copagolperu.inscripciones.domain.repository.EquipoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EliminarEntrenadorService {
    private final EntrenadorRepository entrenadorRepository;
    private final EquipoRepository equipoRepository;

    @Transactional
    public void eliminarEntrenador(UUID entrenadorId) {
        List<Equipo> equipos = equipoRepository.findByEntrenadorId(entrenadorId);

        for (Equipo equipo : equipos) {
            equipo.setEntrenador(null);
        }

        entrenadorRepository.deleteById(entrenadorId);
    }
}
