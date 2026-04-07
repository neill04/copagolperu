package com.neill.copagolperu.inscripciones.domain.repository;

import com.neill.copagolperu.inscripciones.domain.model.Delegado;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DelegadoRepository {
    Delegado save(Delegado delegado);
    Optional<Delegado> findById(UUID id);
    List<Delegado> findByAcademiaId(UUID academiaId);
    List<Delegado> findAll();
    void deleteById(UUID id);
}