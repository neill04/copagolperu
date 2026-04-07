package com.neill.copagolperu.inscripciones.infrastructure.repository;

import com.neill.copagolperu.inscripciones.domain.model.Jugador;
import com.neill.copagolperu.inscripciones.domain.repository.JugadorRepository;
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
    public List<Jugador> findByEquipo_Academia_Id(UUID academiaId) {
        return jpa.findByEquipo_Academia_Id(academiaId);
    }

    @Override
    public List<Jugador> findAll() {
        return jpa.findAll();
    }

    @Override
    public boolean existsByDniAndActivoTrue(String dni) {
        return jpa.existsByDniAndActivoTrue(dni);
    }

    @Override
    public void deleteById(UUID id) {
        jpa.deleteById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return jpa.existsById(id);
    }
}