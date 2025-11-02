package com.neill.copagolperu.application.service.academia.apoderado;

import com.neill.copagolperu.domain.repository.ApoderadoRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EliminarApoderadoService {

    private final ApoderadoRepository apoderadoRepository;

    public EliminarApoderadoService(ApoderadoRepository apoderadoRepository) {
        this.apoderadoRepository = apoderadoRepository;
    }

    public void eliminarApoderado(UUID id) {
        apoderadoRepository.deleteById(id);
    }
}