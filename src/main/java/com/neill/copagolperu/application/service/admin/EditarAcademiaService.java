package com.neill.copagolperu.application.service.admin;

import com.neill.copagolperu.application.dto.AcademiaDTO;
import com.neill.copagolperu.application.mapper.AcademiaMapper;
import com.neill.copagolperu.domain.model.Academia;
import com.neill.copagolperu.domain.model.ubicacion.Distrito;
import com.neill.copagolperu.domain.repository.AcademiaRepository;
import com.neill.copagolperu.domain.repository.DistritoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EditarAcademiaService {

    private final AcademiaRepository academiaRepository;
    private final DistritoRepository distritoRepository;

    public EditarAcademiaService(AcademiaRepository academiaRepository,
                                 DistritoRepository distritoRepository ) {
        this.academiaRepository = academiaRepository;
        this.distritoRepository = distritoRepository;
    }

    public AcademiaDTO editarAcademia(Long id, AcademiaDTO academiaDTO) {
        Academia academia = academiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Academia not found"));

        Distrito distrito = distritoRepository.findById(academiaDTO.getDistritoId())
                .orElseThrow(() -> new RuntimeException("Distrito not found"));

        academia.setNombreAcademia(academiaDTO.getNombreAcademia());
        academia.setNombreRepresentante(academiaDTO.getNombreRepresentante());
        academia.setDniRepresentante(academiaDTO.getDniRepresentante());
        academia.setLogoUrl(academiaDTO.getLogoUrl());
        academia.setEstado(Academia.EstadoAcademia.valueOf(academiaDTO.getEstado()));
        academia.setFechaActualizacion(LocalDateTime.now());
        academia.setDistrito(distrito);

        Academia academiaActualizada = academiaRepository.save(academia);

        return AcademiaMapper.toDTO(academiaActualizada);
    }
}