package com.neill.copagolperu.inscripciones.application.service.academia;

import com.neill.copagolperu.inscripciones.application.dto.request.AcademiaRequest;
import com.neill.copagolperu.inscripciones.application.dto.response.AcademiaResponse;
import com.neill.copagolperu.shared.infrastructure.exception.DniYaRegistradoException;
import com.neill.copagolperu.inscripciones.application.mapper.AcademiaMapper;
import com.neill.copagolperu.inscripciones.domain.model.Academia;
import com.neill.copagolperu.shared.domain.model.ubicacion.Distrito;
import com.neill.copagolperu.inscripciones.domain.repository.AcademiaRepository;
import com.neill.copagolperu.shared.domain.repository.ubicacion.DistritoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RegistrarAcademiaService {

    private final AcademiaRepository academiaRepository;
    private final DistritoRepository distritoRepository;

    public RegistrarAcademiaService(AcademiaRepository academiaRepository,
                                    DistritoRepository distritoRepository) {
        this.academiaRepository = academiaRepository;
        this.distritoRepository = distritoRepository;
    }

    public AcademiaResponse registrarAcademia(AcademiaRequest request) {
        Distrito distrito = distritoRepository.findById(request.distritoId())
                        .orElseThrow(() -> new RuntimeException("Distrito not found"));

        if (academiaRepository.existsByDniRepresentante(request.dniRepresentante())) {
            throw new DniYaRegistradoException();
        }

        Academia academia = AcademiaMapper.toEntity(request);
        academia.setActivo(true);
        academia.setFechaRegistro(LocalDate.now());
        academia.setFechaActualizacion(LocalDate.now());
        academia.setDistrito(distrito);

        Academia newAcademia = academiaRepository.save(academia);

        return AcademiaMapper.toResponse(newAcademia);
    }
}