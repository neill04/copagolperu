package com.neill.copagolperu.application.service.academia.entrenador;

import com.neill.copagolperu.application.dto.request.EntrenadorRequest;
import com.neill.copagolperu.application.dto.response.EntrenadorResponse;
import com.neill.copagolperu.application.exception.DniYaRegistradoException;
import com.neill.copagolperu.application.mapper.EntrenadorMapper;
import com.neill.copagolperu.domain.model.Academia;
import com.neill.copagolperu.domain.model.Entrenador;
import com.neill.copagolperu.domain.repository.AcademiaRepository;
import com.neill.copagolperu.domain.repository.EntrenadorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class RegistrarEntrenadorService {

    private final EntrenadorRepository entrenadorRepository;
    private final AcademiaRepository academiaRepository;

    public RegistrarEntrenadorService(EntrenadorRepository entrenadorRepository,
                                      AcademiaRepository academiaRepository) {
        this.entrenadorRepository = entrenadorRepository;
        this.academiaRepository = academiaRepository;
    }

    public EntrenadorResponse registrarEntrenador(UUID academiaId, EntrenadorRequest request) {
        Academia academia = academiaRepository.findById(academiaId)
                .orElseThrow(() -> new RuntimeException("Academia not found"));

        if (entrenadorRepository.existsByDni(request.dni())) {
            throw new DniYaRegistradoException();
        }

        Entrenador entrenador = EntrenadorMapper.toEntity(request);
        entrenador.setAcademia(academia);
        entrenador.setFechaRegistro(LocalDate.now());
        entrenador.setFechaActualizacion(LocalDate.now());

        Entrenador newEntrenador = entrenadorRepository.save(entrenador);

        return EntrenadorMapper.toResponse(newEntrenador);
    }
}