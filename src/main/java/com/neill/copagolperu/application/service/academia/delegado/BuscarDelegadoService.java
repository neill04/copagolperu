package com.neill.copagolperu.application.service.academia.delegado;

import com.neill.copagolperu.application.dto.response.DelegadoResponse;
import com.neill.copagolperu.application.mapper.DelegadoMapper;
import com.neill.copagolperu.domain.repository.DelegadoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BuscarDelegadoService {

    private final DelegadoRepository delegadoRepository;

    public BuscarDelegadoService(DelegadoRepository delegadoRepository) {
        this.delegadoRepository = delegadoRepository;
    }

    public Optional<DelegadoResponse> buscarDelegado(UUID id) {
        return delegadoRepository.findById(id)
                .map(DelegadoMapper::toResponse);
    }
}