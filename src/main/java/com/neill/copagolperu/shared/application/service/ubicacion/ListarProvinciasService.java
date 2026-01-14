package com.neill.copagolperu.shared.application.service.ubicacion;

import com.neill.copagolperu.shared.application.dto.UbicacionDTO;
import com.neill.copagolperu.shared.domain.repository.ubicacion.ProvinciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarProvinciasService {

    private final ProvinciaRepository provinciaRepository;

    public ListarProvinciasService(ProvinciaRepository provinciaRepository) {
        this.provinciaRepository = provinciaRepository;
    }

    public List<UbicacionDTO.ProvinciaDTO> listarProvinciasPorDepartamentoId(Long departamentoId) {
        return provinciaRepository.findByDepartamentoId(departamentoId)
                .stream()
                .map(provincia -> new UbicacionDTO.ProvinciaDTO(
                        provincia.getId(),
                        provincia.getNombreProvincia()
                ))
                .collect(Collectors.toList());
    }
}