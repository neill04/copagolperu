package com.neill.copagolperu.inscripciones.infrastructure.repository;

import com.neill.copagolperu.inscripciones.domain.model.Categoria;
import com.neill.copagolperu.inscripciones.domain.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataEquipoRepository extends JpaRepository<Equipo, UUID> {
    Optional<Equipo> findByAcademiaIdAndCategoria(UUID academiaId, Categoria categoria);
    List<Equipo> findByAcademiaId(UUID academiaId);
    long countByAcademiaId(UUID academiaId);
}