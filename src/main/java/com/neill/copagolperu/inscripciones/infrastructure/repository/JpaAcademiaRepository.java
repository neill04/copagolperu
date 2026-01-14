package com.neill.copagolperu.inscripciones.infrastructure.repository;

import com.neill.copagolperu.inscripciones.domain.model.Academia;
import com.neill.copagolperu.inscripciones.domain.repository.AcademiaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaAcademiaRepository implements AcademiaRepository {

    private final SpringDataAcademiaRepository jpa;

    public JpaAcademiaRepository(SpringDataAcademiaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Academia save(Academia academia) {
        return jpa.save(academia);
    }

    @Override
    public Optional<Academia> findById(UUID id) {
        return jpa.findById(id);
    }

    @Override
    public List<Academia> findAll() {
        return jpa.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        jpa.deleteById(id);
    }

    @Override
    public boolean existsByDniRepresentante(String dni) {
        return jpa.existsByDniRepresentante(dni);
    }
}