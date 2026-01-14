package com.neill.copagolperu.inscripciones.application.service.entrenador;

import com.neill.copagolperu.inscripciones.application.dto.response.EntrenadorResponse;
import com.neill.copagolperu.inscripciones.application.mapper.EntrenadorMapper;
import com.neill.copagolperu.inscripciones.domain.repository.EntrenadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarTodosLosEntrenadoresService {
    private final EntrenadorRepository entrenadorRepository;

    public ListarTodosLosEntrenadoresService(EntrenadorRepository entrenadorRepository) {
        this.entrenadorRepository = entrenadorRepository;
    }

    public List<EntrenadorResponse> listarTodosLosEntrenadores() {
        return entrenadorRepository.findAll()
                .stream()
                .map(EntrenadorMapper::toResponse)
                .toList();
    }
}