package com.neill.copagolperu.domain.repository;

import com.neill.copagolperu.domain.model.ubicacion.Distrito;

import java.util.Optional;

public interface DistritoRepository {
    Optional<Distrito> findById(Long id);
}