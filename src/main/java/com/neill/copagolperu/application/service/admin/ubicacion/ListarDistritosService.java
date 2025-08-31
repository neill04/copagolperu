package com.neill.copagolperu.application.service.admin.ubicacion;

import com.neill.copagolperu.application.dto.UbicacionDTO;
import com.neill.copagolperu.domain.repository.ubicacion.DistritoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarDistritosService {

    private final DistritoRepository distritoRepository;

    public ListarDistritosService(DistritoRepository distritoRepository) {
        this.distritoRepository = distritoRepository;
    }

    public List<UbicacionDTO.DistritoDTO> listarDistritosPorProvinciaId(Long provinciaId) {
        return distritoRepository.findByProvinciaId(provinciaId)
                .stream()
                .map(distrito -> new UbicacionDTO.DistritoDTO(
                        distrito.getId(),
                        distrito.getNombreDistrito()
                ))
                .collect(Collectors.toList());
    }
}