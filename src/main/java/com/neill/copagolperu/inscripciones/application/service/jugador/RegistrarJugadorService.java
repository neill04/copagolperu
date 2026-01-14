package com.neill.copagolperu.inscripciones.application.service.jugador;

import com.neill.copagolperu.inscripciones.application.dto.request.JugadorRequest;
import com.neill.copagolperu.inscripciones.application.dto.response.JugadorResponse;
import com.neill.copagolperu.shared.infrastructure.exception.EdadNoPermitidaException;
import com.neill.copagolperu.shared.infrastructure.exception.JugadorYaRegistradoException;
import com.neill.copagolperu.inscripciones.application.mapper.JugadorMapper;
import com.neill.copagolperu.inscripciones.domain.model.Categoria;
import com.neill.copagolperu.inscripciones.domain.model.Equipo;
import com.neill.copagolperu.inscripciones.domain.model.Jugador;
import com.neill.copagolperu.inscripciones.domain.repository.EquipoRepository;
import com.neill.copagolperu.inscripciones.domain.repository.JugadorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class RegistrarJugadorService {

    private final JugadorRepository jugadorRepository;
    private final EquipoRepository equipoRepository;
    //private final ApoderadoRepository apoderadoRepository;

    public RegistrarJugadorService(JugadorRepository jugadorRepository,
                                   //ApoderadoRepository apoderadoRepository,
                                   EquipoRepository equipoRepository) {
        this.jugadorRepository = jugadorRepository;
        //this.apoderadoRepository = apoderadoRepository;
        this.equipoRepository = equipoRepository;
    }

    public JugadorResponse registrarJugador(UUID equipoId, JugadorRequest request) {
        Equipo equipo = equipoRepository.findById(equipoId)
                .orElseThrow(() -> new RuntimeException("Equipo not found"));

        validarEdadParaCategoria(request.fechaNacimiento(), equipo.getCategoria(), request.dni());

        /*
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
        */

        if (jugadorRepository.existsByDniAndActivoTrue(request.dni())) {
            throw new JugadorYaRegistradoException(request.dni());
        }

        Jugador jugador = JugadorMapper.toEntity(request);
        jugador.setActivo(true);
        jugador.setEquipo(equipo);
        //jugador.setApoderado(apoderado);
        jugador.setFechaRegistro(LocalDate.now());
        jugador.setFechaActualizacion(LocalDate.now());

        Jugador newJugador = jugadorRepository.save(jugador);

        return JugadorMapper.toResponse(newJugador);
    }

    private void validarEdadParaCategoria(LocalDate fechaNacimiento, Categoria categoria, String dni) {
        if (fechaNacimiento == null) {
            throw new IllegalArgumentException("La fecha de nacimiento es requerida");
        }

        if (!categoria.esFechaNacimientoValida(fechaNacimiento)) {
            int edadJugador = Categoria.calcularEdad(fechaNacimiento);
            throw new EdadNoPermitidaException(dni, fechaNacimiento, categoria, edadJugador);
        }
    }
}