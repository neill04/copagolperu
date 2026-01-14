package com.neill.copagolperu.inscripciones.domain.repository;

import com.neill.copagolperu.inscripciones.domain.model.Equipo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EquipoRepository {
    Equipo save(Equipo equipo);
    Optional<Equipo> findById(UUID id);
    List<Equipo> findByAcademiaId(UUID idAcademia);
    List<Equipo> findAll();
}