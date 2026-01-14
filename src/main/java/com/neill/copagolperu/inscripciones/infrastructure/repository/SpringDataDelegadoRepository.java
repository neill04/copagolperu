package com.neill.copagolperu.inscripciones.infrastructure.repository;

import com.neill.copagolperu.inscripciones.domain.model.Delegado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataDelegadoRepository extends JpaRepository<Delegado, UUID> {
    List<Delegado> findByAcademiaId(UUID academiaId);
}