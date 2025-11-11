package com.neill.copagolperu.infrastructure.repository;

import com.neill.copagolperu.domain.model.Jugador;
import com.neill.copagolperu.domain.repository.JugadorRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaJugadorRepository implements JugadorRepository {

    private final SpringDataJugadorRepository jpa;

    public JpaJugadorRepository(SpringDataJugadorRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Jugador save(Jugador jugador) {
        return jpa.save(jugador);
    }

    @Override
    public Optional<Jugador> findById(UUID id) {
        return jpa.findById(id);
    }

    @Override
    public List<Jugador> findByEquipoId(UUID equipoId) {
        return jpa.findByEquipoId(equipoId);
    }

    @Override
    public List<Jugador> findAll() {
        return jpa.findAll();
    }

    @Override
    public boolean existsByDniAndActivoTrue(String dni) {
        return jpa.existsByDniAndActivoTrue(dni);
    }
}