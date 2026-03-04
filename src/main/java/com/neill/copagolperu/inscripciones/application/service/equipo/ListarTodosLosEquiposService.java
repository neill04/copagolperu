package com.neill.copagolperu.inscripciones.application.service.equipo;

import com.neill.copagolperu.inscripciones.application.dto.response.EquipoResponse;
import com.neill.copagolperu.inscripciones.application.mapper.EquipoMapper;
import com.neill.copagolperu.inscripciones.domain.repository.EquipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarTodosLosEquiposService {
    private final EquipoRepository equipoRepository;
    private final EquipoMapper equipoMapper;

    public List<EquipoResponse> listarTodosLosEquipos() {
        return equipoRepository.findAll()
                .stream()
                .map(equipoMapper::toResponse)
                .toList();
    }
}