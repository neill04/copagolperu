package com.neill.copagolperu.inscripciones.application.service.equipo;

import com.neill.copagolperu.inscripciones.application.dto.response.EquipoResponse;
import com.neill.copagolperu.inscripciones.application.mapper.EquipoMapper;
import com.neill.copagolperu.inscripciones.domain.repository.EquipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BuscarEquipoService {

    private final EquipoRepository equipoRepository;
    private final EquipoMapper equipoMapper;

    public Optional<EquipoResponse> buscarEquipo(UUID id) {
        return equipoRepository.findById(id)
                .map(equipoMapper::toResponse);
    }
}