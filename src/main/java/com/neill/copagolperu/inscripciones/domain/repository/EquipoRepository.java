package com.neill.copagolperu.inscripciones.domain.repository;

import com.neill.copagolperu.inscripciones.domain.model.Categoria;
import com.neill.copagolperu.inscripciones.domain.model.Equipo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EquipoRepository {
    Equipo save(Equipo equipo);
    Optional<Equipo> findById(UUID id);
    Optional<Equipo> findByAcademiaIdAndCategoria(UUID academiaId, Categoria categoria);
    List<Equipo> findByAcademiaId(UUID idAcademia);
    List<Equipo> findAll();
    void deleteById(UUID id);
    List<Equipo> findByDelegadoId(UUID delegadoId);
    List<Equipo> findByEntrenadorId(UUID entrenadorId);
}