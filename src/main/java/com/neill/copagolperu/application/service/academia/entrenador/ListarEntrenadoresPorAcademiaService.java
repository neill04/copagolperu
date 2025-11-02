package com.neill.copagolperu.application.service.academia.entrenador;

import com.neill.copagolperu.application.dto.response.EntrenadorResponse;
import com.neill.copagolperu.application.mapper.EntrenadorMapper;
import com.neill.copagolperu.domain.repository.EntrenadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListarEntrenadoresPorAcademiaService {

    private final EntrenadorRepository entrenadorRepository;

    public ListarEntrenadoresPorAcademiaService(EntrenadorRepository entrenadorRepository) {
        this.entrenadorRepository = entrenadorRepository;
    }

    public List<EntrenadorResponse> listarEntrenadoresPorAcademia(UUID academiaId) {
        return entrenadorRepository.findByAcademiaId(academiaId)
                .stream()
                .map(EntrenadorMapper::toResponse)
                .toList();
    }
}