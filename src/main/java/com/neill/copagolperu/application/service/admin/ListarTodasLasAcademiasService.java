package com.neill.copagolperu.application.service.admin;

import com.neill.copagolperu.application.dto.response.AcademiaResponse;
import com.neill.copagolperu.application.mapper.AcademiaMapper;
import com.neill.copagolperu.domain.repository.AcademiaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarTodasLasAcademiasService {

    private final AcademiaRepository academiaRepository;

    public ListarTodasLasAcademiasService(AcademiaRepository academiaRepository) {
        this.academiaRepository = academiaRepository;
    }

    public List<AcademiaResponse> listarTodasLasAcademias() {
        return academiaRepository.findAll()
                .stream()
                .map(AcademiaMapper::toResponse)
                .toList();
    }
}