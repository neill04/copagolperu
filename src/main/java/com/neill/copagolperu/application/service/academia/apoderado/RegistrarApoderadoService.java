package com.neill.copagolperu.application.service.academia.apoderado;

import com.neill.copagolperu.application.dto.request.ApoderadoRequest;
import com.neill.copagolperu.application.dto.response.ApoderadoResponse;
import com.neill.copagolperu.application.mapper.ApoderadoMapper;
import com.neill.copagolperu.domain.model.Apoderado;
import com.neill.copagolperu.domain.repository.ApoderadoRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistrarApoderadoService {

    private final ApoderadoRepository apoderadoRepository;

    public RegistrarApoderadoService(ApoderadoRepository apoderadoRepository) {
        this.apoderadoRepository = apoderadoRepository;
    }

    public ApoderadoResponse registrarApoderado(ApoderadoRequest request) {
        Apoderado apoderado = ApoderadoMapper.toEntity(request);

        Apoderado newApoderado = apoderadoRepository.save(apoderado);

        return ApoderadoMapper.toResponse(newApoderado);
    }
}