package com.neill.copagolperu.inscripciones.application.service.jugador;

import com.neill.copagolperu.inscripciones.application.dto.request.JugadorRequest;
import com.neill.copagolperu.inscripciones.application.dto.response.JugadorResponse;
import com.neill.copagolperu.inscripciones.application.mapper.JugadorMapper;
import com.neill.copagolperu.inscripciones.domain.model.Jugador;
import com.neill.copagolperu.inscripciones.domain.repository.JugadorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class EditarJugadorService {

    private final JugadorRepository jugadorRepository;

    public EditarJugadorService(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    public JugadorResponse editarJugador(UUID id, JugadorRequest request) {
        Jugador jugador = jugadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jugador not found"));

        jugador.setDni(request.dni());
        jugador.setApellidos(request.apellidos());
        jugador.setNombres(request.nombres());
        jugador.setFechaNacimiento(request.fechaNacimiento());
        jugador.setNumeroCamiseta(request.numeroCamiseta());
        jugador.setFotoUrl(request.fotoUrl());
        jugador.setFechaActualizacion(LocalDate.now());

        Jugador updatedJugador = jugadorRepository.save(jugador);

        return JugadorMapper.toResponse(updatedJugador);
    }
}