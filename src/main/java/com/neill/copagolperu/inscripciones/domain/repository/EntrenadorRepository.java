package com.neill.copagolperu.inscripciones.domain.repository;

import com.neill.copagolperu.inscripciones.domain.model.Entrenador;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EntrenadorRepository {
    Entrenador save(Entrenador entrenador);
    Optional<Entrenador> findById(UUID id);
    List<Entrenador> findByAcademiaId(UUID idAcademia);
    List<Entrenador> findAll();
    boolean existsByDni(String dni);
}