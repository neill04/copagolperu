package com.neill.copagolperu.shared.domain.repository.ubicacion;

import com.neill.copagolperu.shared.domain.model.ubicacion.Distrito;

import java.util.List;
import java.util.Optional;

public interface DistritoRepository {
    Optional<Distrito> findById(Long id);
    List<Distrito> findByProvinciaId(Long provinciaId);
}