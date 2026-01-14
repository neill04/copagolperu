package com.neill.copagolperu.inscripciones.application.service.delegado;

import com.neill.copagolperu.inscripciones.application.dto.response.DelegadoResponse;
import com.neill.copagolperu.inscripciones.application.mapper.DelegadoMapper;
import com.neill.copagolperu.inscripciones.domain.repository.DelegadoRepository;
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