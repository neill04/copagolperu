package com.neill.copagolperu.inscripciones.application.service.entrenador;

import com.neill.copagolperu.inscripciones.application.dto.response.EntrenadorResponse;
import com.neill.copagolperu.inscripciones.application.mapper.EntrenadorMapper;
import com.neill.copagolperu.inscripciones.domain.repository.EntrenadorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BuscarEntrenadorService {

    private final EntrenadorRepository entrenadorRepository;

    public BuscarEntrenadorService(EntrenadorRepository entrenadorRepository) {
        this.entrenadorRepository = entrenadorRepository;
    }

    public Optional<EntrenadorResponse> buscarEntrenador(UUID id) {
        return entrenadorRepository.findById(id)
                .map(EntrenadorMapper::toResponse);
    }
}