package com.neill.copagolperu.infrastructure.repository;

import com.neill.copagolperu.domain.model.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataEntrenadorRepository extends JpaRepository<Entrenador, UUID> {
    List<Entrenador> findByAcademiaId(UUID academiaId);
    boolean existsByDni(String dni);
}