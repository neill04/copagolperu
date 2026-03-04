package com.neill.copagolperu.inscripciones.application.service.equipo;

import com.neill.copagolperu.inscripciones.domain.model.Categoria;
import com.neill.copagolperu.inscripciones.domain.model.Equipo;
import com.neill.copagolperu.inscripciones.domain.model.Jugador;
import com.neill.copagolperu.inscripciones.domain.model.Refuerzo;
import com.neill.copagolperu.inscripciones.domain.repository.EquipoRepository;
import com.neill.copagolperu.inscripciones.domain.repository.JugadorRepository;
import com.neill.copagolperu.inscripciones.infrastructure.repository.RefuerzoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefuerzoService {
    private final RefuerzoRepository refuerzoRepository;
    private final EquipoRepository equipoRepository;
    private final JugadorRepository jugadorRepository;

    @Transactional
    public Refuerzo registrarRefuerzo(UUID jugadorId, UUID equipoDestinoId) {

        Equipo equipoDestino = equipoRepository.findById(equipoDestinoId)
                .orElseThrow(() -> new RuntimeException("Equipo destino no encontrado"));

        Jugador jugador = jugadorRepository.findById(jugadorId)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));

        if (refuerzoRepository.countByEquipoDestinoId(equipoDestinoId) >= 3) {
            throw new IllegalStateException("Límite alcanzado: El equipo " +
                    equipoDestino.getCategoria() + " ya cuenta con 3 refuerzos.");
        }

        if (refuerzoRepository.existsByJugadorId(jugadorId)) {
            throw new IllegalArgumentException("El jugador ya está registrado como refuerzo en otro equipo.");
        }

        validarJerarquiaCategoria(jugador.getEquipo().getCategoria(), equipoDestino.getCategoria());

        if (!jugador.getEquipo().getAcademia().getId().equals(equipoDestino.getAcademia().getId())) {
            throw new IllegalArgumentException("El refuerzo debe pertenecer a la misma academia.");
        }

        Refuerzo refuerzo = Refuerzo.builder()
                .jugador(jugador)
                .equipoDestino(equipoDestino)
                .fechaRegistro(LocalDate.now())
                .build();

        return refuerzoRepository.save(refuerzo);
    }

    private void validarJerarquiaCategoria(Categoria categoriaOrigen, Categoria categoriaDestino) {
        if (!categoriaDestino.esInmediataInferior(categoriaOrigen)) {
            throw new IllegalArgumentException(
                    String.format("Incompatibilidad: La categoría %s no puede ser reforzada por la %s. Solo se permite la categoría inmediata inferior.",
                            categoriaDestino.name(), categoriaOrigen.name())
            );
        }
    }
}
