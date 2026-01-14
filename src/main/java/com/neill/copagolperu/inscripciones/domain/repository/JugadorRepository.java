package com.neill.copagolperu.inscripciones.domain.repository;

import com.neill.copagolperu.inscripciones.domain.model.Jugador;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JugadorRepository {
    Jugador save(Jugador jugador);
    Optional<Jugador> findById(UUID id);
    List<Jugador> findByEquipoId(UUID equipoId);
    List<Jugador> findByEquipo_Academia_Id(UUID academiaId);
    List<Jugador> findAll();
    boolean existsByDniAndActivoTrue(String dni);
}