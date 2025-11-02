package com.neill.copagolperu.application.service.academia.delegado;

import com.neill.copagolperu.application.dto.request.DelegadoRequest;
import com.neill.copagolperu.application.dto.response.DelegadoResponse;
import com.neill.copagolperu.application.mapper.DelegadoMapper;
import com.neill.copagolperu.domain.model.Academia;
import com.neill.copagolperu.domain.model.Delegado;
import com.neill.copagolperu.domain.repository.AcademiaRepository;
import com.neill.copagolperu.domain.repository.DelegadoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class RegistrarDelegadoService {

    private final DelegadoRepository delegadoRepository;
    private final AcademiaRepository academiaRepository;

    public RegistrarDelegadoService(DelegadoRepository delegadoRepository,
                                    AcademiaRepository academiaRepository) {
        this.delegadoRepository = delegadoRepository;
        this.academiaRepository = academiaRepository;
    }

    public DelegadoResponse registrarDelegado(UUID academiaId, DelegadoRequest request) {
        Delegado delegado = DelegadoMapper.toEntity(request);

        Academia academia = academiaRepository.findById(academiaId)
                .orElseThrow(() -> new RuntimeException("Academia not found"));
        delegado.setAcademia(academia);

        delegado.setFechaRegistro(LocalDate.now());
        delegado.setFechaActualizacion(LocalDate.now());

        Delegado newDelegado = delegadoRepository.save(delegado);

        return DelegadoMapper.toResponse(newDelegado);
    }
}