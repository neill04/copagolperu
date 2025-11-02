package com.neill.copagolperu.application.service.academia.equipo;

import com.neill.copagolperu.application.dto.request.EquipoRequest;
import com.neill.copagolperu.application.dto.response.EquipoResponse;
import com.neill.copagolperu.application.mapper.EquipoMapper;
import com.neill.copagolperu.domain.model.Delegado;
import com.neill.copagolperu.domain.model.Entrenador;
import com.neill.copagolperu.domain.model.Equipo;
import com.neill.copagolperu.domain.repository.DelegadoRepository;
import com.neill.copagolperu.domain.repository.EntrenadorRepository;
import com.neill.copagolperu.domain.repository.EquipoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class EditarEquipoService {

    private final EquipoRepository equipoRepository;
    private final EntrenadorRepository entrenadorRepository;
    private final DelegadoRepository delegadoRepository;

    public EditarEquipoService(EquipoRepository equipoRepository,
                               EntrenadorRepository entrenadorRepository,
                               DelegadoRepository delegadoRepository) {
        this.equipoRepository = equipoRepository;
        this.entrenadorRepository = entrenadorRepository;
        this.delegadoRepository = delegadoRepository;
    }

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

        return EquipoMapper.toResponse(updatedEquipo);
    }
}