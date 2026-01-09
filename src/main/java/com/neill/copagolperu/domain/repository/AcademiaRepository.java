package com.neill.copagolperu.domain.repository;

import com.neill.copagolperu.domain.model.Academia;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AcademiaRepository {
    Academia save(Academia academia);
    Optional<Academia> findById(UUID id);
    List<Academia> findAll();
    void deleteById(UUID id);
    boolean existsByDniRepresentante(String dni);
}

