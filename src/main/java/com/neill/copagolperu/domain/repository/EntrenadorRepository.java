package com.neill.copagolperu.domain.repository;

import com.neill.copagolperu.domain.model.Entrenador;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EntrenadorRepository {
    Entrenador save(Entrenador entrenador);
    Optional<Entrenador> findById(UUID id);
    List<Entrenador> findByAcademiaId(UUID idAcademia);
}