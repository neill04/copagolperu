package com.neill.copagolperu.infrastructure.repository;

import com.neill.copagolperu.domain.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataEquipoRepository extends JpaRepository<Equipo, UUID> {
    List<Equipo> findByAcademiaId(UUID academiaId);
    long countByAcademiaId(UUID academiaId);
}