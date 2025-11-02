package com.neill.copagolperu.domain.repository;

import com.neill.copagolperu.domain.model.Equipo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EquipoRepository {
    Equipo save(Equipo equipo);
    Optional<Equipo> findById(UUID id);
    List<Equipo> findByAcademiaId(UUID idAcademia);
}