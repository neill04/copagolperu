package com.neill.copagolperu.inscripciones.application.service.academia;

import com.neill.copagolperu.inscripciones.application.dto.response.AcademiaResponse;
import com.neill.copagolperu.inscripciones.application.mapper.AcademiaMapper;
import com.neill.copagolperu.inscripciones.domain.repository.AcademiaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BuscarAcademiaService {
    private final AcademiaRepository academiaRepository;

    public BuscarAcademiaService(AcademiaRepository academiaRepository) {
        this.academiaRepository = academiaRepository;
    }

    public Optional<AcademiaResponse> buscarAcademia(UUID id) {
        return academiaRepository.findById(id)
                .map(AcademiaMapper::toResponse);
    }
}