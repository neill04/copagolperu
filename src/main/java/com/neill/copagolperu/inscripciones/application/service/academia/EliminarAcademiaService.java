package com.neill.copagolperu.inscripciones.application.service.academia;

import com.neill.copagolperu.inscripciones.domain.repository.AcademiaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EliminarAcademiaService {

    private final AcademiaRepository academiaRepository;

    public EliminarAcademiaService(AcademiaRepository academiaRepository) {
        this.academiaRepository = academiaRepository;
    }

    public void eliminarAcademia(UUID id) {
        academiaRepository.deleteById(id);
    }
}
