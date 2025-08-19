package com.neill.copagolperu.application.mapper;

import com.neill.copagolperu.application.dto.AcademiaDTO;
import com.neill.copagolperu.domain.model.Academia;

import java.time.LocalDateTime;

public class AcademiaMapper {

    // Entidad -> DTO
    public static AcademiaDTO toDTO(Academia academia) {
        return new AcademiaDTO(
                academia.getId(),
                academia.getNombreAcademia(),
                academia.getNombreRepresentante(),
                academia.getDniRepresentante(),
                academia.getLogoUrl(),
                academia.getEstado().name(),
                academia.getFechaRegistro(),
                academia.getFechaActualizacion(),
                academia.getDistrito().getId(),
                academia.getDistrito().getNombreDistrito()
        );
    }

    // DTO -> Entidad
    public static Academia toEntity(AcademiaDTO academiaDTO) {
        Academia academia = new Academia();
        academia.setNombreAcademia(academiaDTO.getNombreAcademia());
        academia.setNombreRepresentante(academiaDTO.getNombreRepresentante());
        academia.setDniRepresentante(academiaDTO.getDniRepresentante());
        academia.setLogoUrl(academiaDTO.getLogoUrl());
        academia.setEstado(Academia.EstadoAcademia.valueOf(academiaDTO.getEstado()));
        academia.setFechaRegistro(LocalDateTime.now());
        academia.setFechaActualizacion(LocalDateTime.now());
        return academia;
    }
}