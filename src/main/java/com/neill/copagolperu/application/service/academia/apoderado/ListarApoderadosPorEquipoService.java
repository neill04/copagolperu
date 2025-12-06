/*
package com.neill.copagolperu.application.service.academia.apoderado;

import com.neill.copagolperu.application.dto.response.ApoderadoResponse;
import com.neill.copagolperu.application.mapper.ApoderadoMapper;
import com.neill.copagolperu.domain.repository.ApoderadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListarApoderadosPorEquipoService {

    private final ApoderadoRepository apoderadoRepository;

    public ListarApoderadosPorEquipoService(ApoderadoRepository apoderadoRepository) {
        this.apoderadoRepository = apoderadoRepository;
    }

    public List<ApoderadoResponse> listarApoderadosPorEquipo(UUID equipoId) {
        return apoderadoRepository.findDistinctByEquipoId(equipoId)
                .stream()
                .map(ApoderadoMapper::toResponse)
                .toList();
    }
}
*/