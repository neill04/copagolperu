package com.neill.copagolperu.inscripciones.infrastructure.repository;

import com.neill.copagolperu.inscripciones.domain.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataJugadorRepository extends JpaRepository<Jugador, UUID> {
    List<Jugador> findByEquipoId(UUID equipoId);
    List<Jugador> findByEquipo_Academia_Id(UUID academiaId);
    boolean existsByDniAndActivoTrue(String dni);
}