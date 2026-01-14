package com.neill.copagolperu.inscripciones.application.service.academia;

import com.neill.copagolperu.inscripciones.application.dto.request.AcademiaRequest;
import com.neill.copagolperu.inscripciones.application.dto.response.AcademiaResponse;
import com.neill.copagolperu.shared.infrastructure.exception.AcademiaNotFoundException;
import com.neill.copagolperu.inscripciones.application.mapper.AcademiaMapper;
import com.neill.copagolperu.inscripciones.domain.model.Academia;
import com.neill.copagolperu.shared.domain.model.ubicacion.Distrito;
import com.neill.copagolperu.inscripciones.domain.repository.AcademiaRepository;
import com.neill.copagolperu.shared.domain.repository.ubicacion.DistritoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class EditarAcademiaService {

    private final AcademiaRepository academiaRepository;
    private final DistritoRepository distritoRepository;

    public EditarAcademiaService(AcademiaRepository academiaRepository,
                                 DistritoRepository distritoRepository ) {
        this.academiaRepository = academiaRepository;
        this.distritoRepository = distritoRepository;
    }

    public AcademiaResponse editarAcademia(UUID id, AcademiaRequest request) {
        Academia academia = academiaRepository.findById(id)
                .orElseThrow(() -> new AcademiaNotFoundException("Academia not found"));

        if (request.distritoId() != null) {
            Distrito distrito = distritoRepository.findById(request.distritoId())
                    .orElseThrow(() -> new RuntimeException("Distrito not found"));
            academia.setDistrito(distrito);
        }

        academia.setNombreAcademia(request.nombreAcademia());
        academia.setNombreRepresentante(request.nombreRepresentante());
        academia.setDniRepresentante(request.dniRepresentante());
        academia.setTelefonoRepresentante(request.telefonoRepresentante());
        academia.setLiga(request.liga());
        academia.setLogoUrl(request.logoUrl());
        academia.setActivo(request.activo());
        academia.setFechaActualizacion(LocalDate.now());

        Academia updatedAcademia = academiaRepository.save(academia);

        return AcademiaMapper.toResponse(updatedAcademia);
    }
}