package com.neill.copagolperu.inscripciones.application.service.equipo;

import com.neill.copagolperu.inscripciones.application.dto.response.EquipoResponse;
import com.neill.copagolperu.inscripciones.application.mapper.EquipoMapper;
import com.neill.copagolperu.inscripciones.domain.repository.EquipoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarTodosLosEquiposService {
    private final EquipoRepository equipoRepository;

    public ListarTodosLosEquiposService(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    public List<EquipoResponse> listarTodosLosEquipos() {
        return equipoRepository.findAll()
                .stream()
                .map(EquipoMapper::toResponse)
                .toList();
    }
}