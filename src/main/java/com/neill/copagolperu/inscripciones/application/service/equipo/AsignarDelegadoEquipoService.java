package com.neill.copagolperu.inscripciones.application.service.equipo;

import com.neill.copagolperu.inscripciones.domain.model.Delegado;
import com.neill.copagolperu.inscripciones.domain.model.Equipo;
import com.neill.copagolperu.inscripciones.domain.repository.DelegadoRepository;
import com.neill.copagolperu.inscripciones.domain.repository.EquipoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AsignarDelegadoEquipoService {

    private final EquipoRepository equipoRepository;
    private final DelegadoRepository delegadoRepository;

    @Transactional
    public void asignarDelegado(UUID equipoId, UUID delegadoId) {
        Equipo equipo = equipoRepository.findById(equipoId)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        Delegado delegado = delegadoRepository.findById(delegadoId)
                .orElseThrow(() -> new RuntimeException("Delegado no encontrado"));

        equipo.setDelegado(delegado);
        equipo.setFechaActualizacion(LocalDate.now());
    }
}
