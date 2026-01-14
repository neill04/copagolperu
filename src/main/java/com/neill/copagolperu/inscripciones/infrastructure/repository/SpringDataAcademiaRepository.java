package com.neill.copagolperu.inscripciones.infrastructure.repository;

import com.neill.copagolperu.inscripciones.domain.model.Academia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataAcademiaRepository extends JpaRepository<Academia, UUID> {
    boolean existsByDniRepresentante(String dni);
}