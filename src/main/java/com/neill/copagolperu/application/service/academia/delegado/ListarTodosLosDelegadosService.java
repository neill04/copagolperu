package com.neill.copagolperu.application.service.academia.delegado;

import com.neill.copagolperu.application.dto.response.DelegadoResponse;
import com.neill.copagolperu.application.mapper.DelegadoMapper;
import com.neill.copagolperu.domain.repository.DelegadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarTodosLosDelegadosService {
    private final DelegadoRepository delegadoRepository;

    public ListarTodosLosDelegadosService(DelegadoRepository delegadoRepository) {
        this.delegadoRepository = delegadoRepository;
    }

    public List<DelegadoResponse> listarTodosLosDelegados() {
        return delegadoRepository.findAll()
                .stream()
                .map(DelegadoMapper::toResponse)
                .toList();
    }
}