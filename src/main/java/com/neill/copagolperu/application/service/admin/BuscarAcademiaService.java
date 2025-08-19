package com.neill.copagolperu.application.service.admin;

import com.neill.copagolperu.application.dto.AcademiaDTO;
import com.neill.copagolperu.application.mapper.AcademiaMapper;
import com.neill.copagolperu.domain.repository.AcademiaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarAcademiaService {
    private final AcademiaRepository academiaRepository;

    public BuscarAcademiaService(AcademiaRepository academiaRepository) {
        this.academiaRepository = academiaRepository;
    }

    public Optional<AcademiaDTO> buscarAcademia(Long id) {
        return academiaRepository.findById(id)
                .map(AcademiaMapper::toDTO);
    }
}