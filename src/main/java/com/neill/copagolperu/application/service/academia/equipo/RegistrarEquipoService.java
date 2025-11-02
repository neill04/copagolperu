package com.neill.copagolperu.application.service.academia.equipo;

import com.neill.copagolperu.application.dto.request.EquipoRequest;
import com.neill.copagolperu.application.dto.response.EquipoResponse;
import com.neill.copagolperu.application.mapper.EquipoMapper;
import com.neill.copagolperu.domain.model.Academia;
import com.neill.copagolperu.domain.model.Delegado;
import com.neill.copagolperu.domain.model.Entrenador;
import com.neill.copagolperu.domain.model.Equipo;
import com.neill.copagolperu.domain.repository.EquipoRepository;
import com.neill.copagolperu.domain.repository.AcademiaRepository;
import com.neill.copagolperu.domain.repository.EntrenadorRepository;
import com.neill.copagolperu.domain.repository.DelegadoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;


@Service
public class RegistrarEquipoService {

    private final EquipoRepository equipoRepository;
    private final AcademiaRepository academiaRepository;
    private final EntrenadorRepository entrenadorRepository;
    private final DelegadoRepository delegadoRepository;

    public RegistrarEquipoService(EquipoRepository equipoRepository,
                                  AcademiaRepository academiaRepository,
                                  EntrenadorRepository entrenadorRepository,
                                  DelegadoRepository delegadoRepository) {
        this.equipoRepository = equipoRepository;
        this.academiaRepository = academiaRepository;
        this.entrenadorRepository = entrenadorRepository;
        this.delegadoRepository = delegadoRepository;
    }


    public EquipoResponse registrarEquipo(UUID academiaId, EquipoRequest request) {
        Equipo equipo = EquipoMapper.toEntity(request);

        Academia academia = academiaRepository.findById(academiaId)
                .orElseThrow(() -> new RuntimeException("Academia not found"));
        equipo.setAcademia(academia);

        Entrenador entrenador = entrenadorRepository.findById(request.entrenadorId())
                .orElseThrow(() -> new RuntimeException("Entrenador not found"));
        equipo.setEntrenador(entrenador);

        Delegado delegado = delegadoRepository.findById(request.delegadoId())
                .orElseThrow(() -> new RuntimeException("Delegado not found"));
        equipo.setDelegado(delegado);

        equipo.setActivo(true);
        equipo.setFechaRegistro(LocalDate.now());
        equipo.setFechaActualizacion(LocalDate.now());
        Equipo newEquipo = equipoRepository.save(equipo);

        return EquipoMapper.toResponse(newEquipo);
    }
}
