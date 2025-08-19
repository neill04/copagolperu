package com.neill.copagolperu.application.service.admin;

import com.neill.copagolperu.application.dto.AcademiaDTO;
import com.neill.copagolperu.application.mapper.AcademiaMapper;
import com.neill.copagolperu.domain.repository.AcademiaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarAcademiasService {
    private final AcademiaRepository academiaRepository;

    public ListarAcademiasService(AcademiaRepository academiaRepository) {
        this.academiaRepository = academiaRepository;
    }

    public List<AcademiaDTO> listarAcademias() {
        return academiaRepository.findAll()
                .stream()
                .map(AcademiaMapper::toDTO)
                .toList();
    }
}