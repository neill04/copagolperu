package com.neill.copagolperu.inscripciones.application.service.equipo;

import com.neill.copagolperu.inscripciones.domain.model.Entrenador;
import com.neill.copagolperu.inscripciones.domain.model.Equipo;
import com.neill.copagolperu.inscripciones.domain.repository.EntrenadorRepository;
import com.neill.copagolperu.inscripciones.domain.repository.EquipoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AsignarEntrenadorEquipoService {

    private final EquipoRepository equipoRepository;
    private final EntrenadorRepository entrenadorRepository;

    @Transactional
    public void asignarEntrenador(UUID equipoId, UUID entrenadorId) {
        Equipo equipo = equipoRepository.findById(equipoId)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        Entrenador entrenador = entrenadorRepository.findById(entrenadorId)
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado"));

        equipo.setEntrenador(entrenador);
        equipo.setFechaActualizacion(LocalDate.now());
    }

}
