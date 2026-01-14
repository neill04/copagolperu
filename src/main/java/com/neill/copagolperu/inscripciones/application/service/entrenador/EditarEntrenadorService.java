package com.neill.copagolperu.inscripciones.application.service.entrenador;

import com.neill.copagolperu.inscripciones.application.dto.request.EntrenadorRequest;
import com.neill.copagolperu.inscripciones.application.dto.response.EntrenadorResponse;
import com.neill.copagolperu.inscripciones.application.mapper.EntrenadorMapper;
import com.neill.copagolperu.inscripciones.domain.model.Entrenador;
import com.neill.copagolperu.inscripciones.domain.repository.EntrenadorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class EditarEntrenadorService {

    private final EntrenadorRepository entrenadorRepository;

    public EditarEntrenadorService(EntrenadorRepository entrenadorRepository) {
        this.entrenadorRepository = entrenadorRepository;
    }

    public EntrenadorResponse editarEntrenador(UUID id, EntrenadorRequest request) {
        Entrenador entrenador = entrenadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrenador not found"));

        entrenador.setDni(request.dni());
        entrenador.setApellidos(request.apellidos());
        entrenador.setNombres(request.nombres());
        entrenador.setLicencia(request.licencia());
        entrenador.setFechaNacimiento(request.fechaNacimiento());
        entrenador.setTelefono(request.telefono());
        entrenador.setEmail(request.email());
        entrenador.setFotoUrl(request.fotoUrl());
        entrenador.setEstadoDisciplina(request.estadoDisciplina());
        entrenador.setObservaciones(request.observaciones());
        entrenador.setFechaActualizacion(LocalDate.now());

        Entrenador updatedEntrenador = entrenadorRepository.save(entrenador);

        return EntrenadorMapper.toResponse(updatedEntrenador);
    }
}