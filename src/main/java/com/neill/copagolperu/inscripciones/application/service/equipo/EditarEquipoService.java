package com.neill.copagolperu.inscripciones.application.service.equipo;

import com.neill.copagolperu.inscripciones.application.dto.request.EquipoRequest;
import com.neill.copagolperu.inscripciones.application.dto.response.EquipoResponse;
import com.neill.copagolperu.inscripciones.application.mapper.EquipoMapper;
import com.neill.copagolperu.inscripciones.domain.model.Delegado;
import com.neill.copagolperu.inscripciones.domain.model.Entrenador;
import com.neill.copagolperu.inscripciones.domain.model.Equipo;
import com.neill.copagolperu.inscripciones.domain.repository.DelegadoRepository;
import com.neill.copagolperu.inscripciones.domain.repository.EntrenadorRepository;
import com.neill.copagolperu.inscripciones.domain.repository.EquipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EditarEquipoService {

    private final EquipoRepository equipoRepository;
    private final EntrenadorRepository entrenadorRepository;
    private final DelegadoRepository delegadoRepository;
    private final EquipoMapper equipoMapper;

    public EquipoResponse editarEquipo(UUID id, EquipoRequest request) {
        Equipo equipo = equipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipo not found"));

        Entrenador entrenador = entrenadorRepository.findById(request.entrenadorId())
                .orElseThrow(() -> new RuntimeException("Entrenador not found"));

        Delegado delegado = delegadoRepository.findById(request.delegadoId())
                .orElseThrow(() -> new RuntimeException("Delegado not found"));

        equipo.setCategoria(request.categoria());
        equipo.setColorCamiseta(request.colorCamiseta());
        equipo.setEntrenador(entrenador);
        equipo.setDelegado(delegado);
        equipo.setFechaActualizacion(LocalDate.now());

        Equipo updatedEquipo = equipoRepository.save(equipo);

        return equipoMapper.toResponse(updatedEquipo);
    }
}