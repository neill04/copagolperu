package com.neill.copagolperu.application.service.academia.jugador;

import com.neill.copagolperu.application.dto.request.JugadorRequest;
import com.neill.copagolperu.application.dto.response.JugadorResponse;
import com.neill.copagolperu.application.mapper.ApoderadoMapper;
import com.neill.copagolperu.application.mapper.JugadorMapper;
import com.neill.copagolperu.domain.model.Apoderado;
import com.neill.copagolperu.domain.model.Equipo;
import com.neill.copagolperu.domain.model.Jugador;
import com.neill.copagolperu.domain.repository.ApoderadoRepository;
import com.neill.copagolperu.domain.repository.EquipoRepository;
import com.neill.copagolperu.domain.repository.JugadorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class RegistrarJugadorService {

    private final JugadorRepository jugadorRepository;
    private final EquipoRepository equipoRepository;
    private final ApoderadoRepository apoderadoRepository;

    public RegistrarJugadorService(JugadorRepository jugadorRepository,
                                   ApoderadoRepository apoderadoRepository,
                                   EquipoRepository equipoRepository) {
        this.jugadorRepository = jugadorRepository;
        this.apoderadoRepository = apoderadoRepository;
        this.equipoRepository = equipoRepository;
    }

    public JugadorResponse registrarJugador(UUID equipoId, JugadorRequest request) {
        Equipo equipo = equipoRepository.findById(equipoId)
                .orElseThrow(() -> new RuntimeException("Equipo not found"));

        Apoderado apoderado = null;

        if(request.apoderadoId() != null) {
            apoderado = apoderadoRepository.findById(request.apoderadoId())
                    .orElseThrow(() ->  new RuntimeException("Apoderado not found"));
        } else if (request.apoderado() != null) {
            apoderado = apoderadoRepository.findByDni(request.apoderado().dni())
                    .orElseGet(() -> apoderadoRepository.save(ApoderadoMapper.toEntity(request.apoderado())));
        } else {
            throw new RuntimeException("Debe de enviar el apoderadoId o apoderado");
        }

        Jugador jugador = JugadorMapper.toEntity(request);
        jugador.setActivo(true);
        jugador.setEquipo(equipo);
        jugador.setApoderado(apoderado);
        jugador.setFechaRegistro(LocalDate.now());
        jugador.setFechaActualizacion(LocalDate.now());

        Jugador newJugador = jugadorRepository.save(jugador);

        return JugadorMapper.toResponse(newJugador);
    }
}