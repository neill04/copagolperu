package com.neill.copagolperu.application.service.academia.delegado;

import com.neill.copagolperu.application.dto.response.DelegadoResponse;
import com.neill.copagolperu.application.mapper.DelegadoMapper;
import com.neill.copagolperu.domain.repository.DelegadoRepository;
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