package com.neill.copagolperu.inscripciones.infrastructure.repository;

import com.neill.copagolperu.inscripciones.domain.model.Entrenador;
import com.neill.copagolperu.inscripciones.domain.repository.EntrenadorRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaEntrenadorRepository implements EntrenadorRepository {

    private final SpringDataEntrenadorRepository jpa;

    public JpaEntrenadorRepository(SpringDataEntrenadorRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Entrenador save(Entrenador entrenador) {
        return jpa.save(entrenador);
    }

    @Override
    public Optional<Entrenador> findById(UUID id) {
        return jpa.findById(id);
    }

    @Override
    public List<Entrenador> findByAcademiaId(UUID idAcademia) {
        return jpa.findByAcademiaId(idAcademia);
    }

    @Override
    public List<Entrenador> findAll() {
        return jpa.findAll();
    }

    @Override
    public boolean existsByDni(String dni) {
        return jpa.existsByDni(dni);
    }

    @Override
    public void deleteById(UUID id) {
        jpa.deleteById(id);
    }
}