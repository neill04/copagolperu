package com.neill.copagolperu.inscripciones.application.service.delegado;

import com.neill.copagolperu.inscripciones.application.dto.request.DelegadoRequest;
import com.neill.copagolperu.inscripciones.application.dto.response.DelegadoResponse;
import com.neill.copagolperu.inscripciones.application.mapper.DelegadoMapper;
import com.neill.copagolperu.inscripciones.domain.model.Delegado;
import com.neill.copagolperu.inscripciones.domain.repository.DelegadoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class EditarDelegadoService {

    private final DelegadoRepository delegadoRepository;

    public EditarDelegadoService(DelegadoRepository delegadoRepository) {
        this.delegadoRepository = delegadoRepository;
    }

    public DelegadoResponse editarDelegado(UUID id, DelegadoRequest request) {
        Delegado delegado = delegadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delegado not found"));

        delegado.setDni(request.dni());
        delegado.setApellidos(request.apellidos());
        delegado.setNombres(request.nombres());
        delegado.setFechaNacimiento(request.fechaNacimiento());
        delegado.setTelefono(request.telefono());
        delegado.setEmail(request.email());
        delegado.setFotoUrl(request.fotoUrl());
        delegado.setFechaActualizacion(LocalDate.now());

        Delegado updatedDelegado = delegadoRepository.save(delegado);

        return DelegadoMapper.toResponse(updatedDelegado);
    }
}