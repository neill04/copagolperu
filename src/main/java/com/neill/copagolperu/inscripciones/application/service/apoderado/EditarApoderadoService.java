/*
package com.neill.copagolperu.application.service.academia.apoderado;

import com.neill.copagolperu.inscripciones.application.dto.request.ApoderadoRequest;
import com.neill.copagolperu.inscripciones.application.dto.response.ApoderadoResponse;
import com.neill.copagolperu.application.mapper.ApoderadoMapper;
import com.neill.copagolperu.domain.model.Apoderado;
import com.neill.copagolperu.domain.repository.ApoderadoRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EditarApoderadoService {

    private final ApoderadoRepository apoderadoRepository;

    public EditarApoderadoService(ApoderadoRepository apoderadoRepository) {
        this.apoderadoRepository = apoderadoRepository;
    }

    public ApoderadoResponse editarApoderado(UUID id, ApoderadoRequest request) {
        Apoderado apoderado = apoderadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Apoderado not found"));

        apoderado.setDni(request.dni());
        apoderado.setApellidos(request.apellidos());
        apoderado.setNombres(request.nombres());
        apoderado.setFechaNacimiento(request.fechaNacimiento());
        apoderado.setParentesco(request.parentesco());
        apoderado.setTelefono(request.telefono());

        Apoderado updatedApoderado = apoderadoRepository.save(apoderado);

        return ApoderadoMapper.toResponse(updatedApoderado);
    }
}
*/