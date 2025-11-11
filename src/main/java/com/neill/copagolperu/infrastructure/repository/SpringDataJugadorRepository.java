package com.neill.copagolperu.infrastructure.repository;

import com.neill.copagolperu.domain.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataJugadorRepository extends JpaRepository<Jugador, UUID> {
    List<Jugador> findByEquipoId(UUID equipoId);

    boolean existsByDniAndActivoTrue(String dni);
}