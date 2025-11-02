package com.neill.copagolperu.application.service.academia.equipo;

import com.neill.copagolperu.application.dto.response.EquipoResponse;
import com.neill.copagolperu.application.mapper.EquipoMapper;
import com.neill.copagolperu.domain.repository.EquipoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListarEquiposPorAcademiaService {

    private final EquipoRepository equipoRepository;

    public ListarEquiposPorAcademiaService(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    public List<EquipoResponse> listarEquiposPorAcademia(UUID academiaId) {
        return equipoRepository.findByAcademiaId(academiaId)
                .stream()
                .map(EquipoMapper::toResponse)
                .toList();
    }
}