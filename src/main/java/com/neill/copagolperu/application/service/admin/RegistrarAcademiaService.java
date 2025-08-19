package com.neill.copagolperu.application.service.admin;

import com.neill.copagolperu.application.dto.AcademiaDTO;
import com.neill.copagolperu.application.mapper.AcademiaMapper;
import com.neill.copagolperu.domain.model.Academia;
import com.neill.copagolperu.domain.model.ubicacion.Distrito;
import com.neill.copagolperu.domain.repository.AcademiaRepository;
import com.neill.copagolperu.domain.repository.DistritoRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistrarAcademiaService {

    private final AcademiaRepository academiaRepository;
    private final DistritoRepository distritoRepository;

    public RegistrarAcademiaService(AcademiaRepository academiaRepository,
                                    DistritoRepository distritoRepository) {
        this.academiaRepository = academiaRepository;
        this.distritoRepository = distritoRepository;
    }

    public AcademiaDTO registrarAcademia(Long distritoId, AcademiaDTO dto) {
        Distrito distrito = distritoRepository.findById(distritoId)
                        .orElseThrow(() -> new RuntimeException("Distrito not found"));

        Academia academia = AcademiaMapper.toEntity(dto);
        academia.setDistrito(distrito);

        Academia academiaRegistrada = academiaRepository.save(academia);

        return AcademiaMapper.toDTO(academiaRegistrada);
    }
}