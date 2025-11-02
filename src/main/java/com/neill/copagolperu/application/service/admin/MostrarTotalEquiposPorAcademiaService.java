package com.neill.copagolperu.application.service.admin;

import com.neill.copagolperu.domain.repository.AcademiaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MostrarTotalEquiposPorAcademiaService {

    private final AcademiaRepository academiaRepository;

    public MostrarTotalEquiposPorAcademiaService(AcademiaRepository academiaRepository) {
        this.academiaRepository = academiaRepository;
    }

    public long mostrarTotalEquiposPorAcademia(UUID id) {
        if ((academiaRepository.findById(id).isEmpty())) {
            return 0L;
        }
        return academiaRepository.countTeamsByAcademia(id);
    }
}