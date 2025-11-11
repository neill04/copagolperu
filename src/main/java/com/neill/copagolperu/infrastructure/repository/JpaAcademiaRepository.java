package com.neill.copagolperu.infrastructure.repository;

import com.neill.copagolperu.domain.model.Academia;
import com.neill.copagolperu.domain.repository.AcademiaRepository;
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
}