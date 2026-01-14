package com.neill.copagolperu.inscripciones.application.service.delegado;

import com.neill.copagolperu.inscripciones.application.dto.response.DelegadoResponse;
import com.neill.copagolperu.inscripciones.application.mapper.DelegadoMapper;
import com.neill.copagolperu.inscripciones.domain.repository.DelegadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListarDelegadosPorAcademiaService {

    private final DelegadoRepository delegadoRepository;

    public ListarDelegadosPorAcademiaService(DelegadoRepository delegadoRepository) {
        this.delegadoRepository = delegadoRepository;
    }

    public List<DelegadoResponse> listarDelegadosPorAcademia(UUID academiaId) {
        return delegadoRepository.findByAcademiaId(academiaId)
                .stream()
                .map(DelegadoMapper::toResponse)
                .toList();
    }
}