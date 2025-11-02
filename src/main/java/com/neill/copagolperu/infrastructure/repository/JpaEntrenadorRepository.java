package com.neill.copagolperu.infrastructure.repository;

import com.neill.copagolperu.domain.model.Entrenador;
import com.neill.copagolperu.domain.repository.EntrenadorRepository;
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
}