package com.neill.copagolperu.inscripciones.application.service.equipo;

import com.neill.copagolperu.inscripciones.application.dto.response.EquipoResponse;
import com.neill.copagolperu.inscripciones.application.mapper.EquipoMapper;
import com.neill.copagolperu.inscripciones.domain.repository.EquipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ListarEquiposPorAcademiaService {

    private final EquipoRepository equipoRepository;
    private final EquipoMapper equipoMapper;


    public List<EquipoResponse> listarEquiposPorAcademia(UUID academiaId) {
        return equipoRepository.findByAcademiaId(academiaId)
                .stream()
                .map(equipoMapper::toResponse)
                .toList();
    }
}