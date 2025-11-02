package com.neill.copagolperu.application.service.admin;

import com.neill.copagolperu.application.dto.request.AcademiaRequest;
import com.neill.copagolperu.application.dto.response.AcademiaResponse;
import com.neill.copagolperu.application.mapper.AcademiaMapper;
import com.neill.copagolperu.domain.model.Academia;
import com.neill.copagolperu.domain.model.ubicacion.Distrito;
import com.neill.copagolperu.domain.repository.AcademiaRepository;
import com.neill.copagolperu.domain.repository.ubicacion.DistritoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RegistrarAcademiaService {

    private final AcademiaRepository academiaRepository;
    private final DistritoRepository distritoRepository;

    public RegistrarAcademiaService(AcademiaRepository academiaRepository,
                                    DistritoRepository distritoRepository) {
        this.academiaRepository = academiaRepository;
        this.distritoRepository = distritoRepository;
    }

    public AcademiaResponse registrarAcademia(AcademiaRequest request) {
        Distrito distrito = distritoRepository.findById(request.distritoId())
                        .orElseThrow(() -> new RuntimeException("Distrito not found"));

        Academia academia = AcademiaMapper.toEntity(request);
        academia.setActivo(true);
        academia.setFechaRegistro(LocalDate.now());
        academia.setFechaActualizacion(LocalDate.now());
        academia.setDistrito(distrito);

        Academia newAcademia = academiaRepository.save(academia);

        return AcademiaMapper.toResponse(newAcademia);
    }
}