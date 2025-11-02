package com.neill.copagolperu.application.service.academia.apoderado;

import com.neill.copagolperu.application.dto.response.ApoderadoResponse;
import com.neill.copagolperu.application.mapper.ApoderadoMapper;
import com.neill.copagolperu.domain.repository.ApoderadoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BuscarApoderadoService {

    private final ApoderadoRepository apoderadoRepository;

    public BuscarApoderadoService(ApoderadoRepository apoderadoRepository) {
        this.apoderadoRepository = apoderadoRepository;
    }

    public Optional<ApoderadoResponse> buscarApoderado(UUID id) {
        return apoderadoRepository.findById(id)
                .map(ApoderadoMapper::toResponse);
    }
}