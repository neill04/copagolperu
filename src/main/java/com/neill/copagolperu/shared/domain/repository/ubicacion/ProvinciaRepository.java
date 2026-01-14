package com.neill.copagolperu.shared.domain.repository.ubicacion;

import com.neill.copagolperu.shared.domain.model.ubicacion.Provincia;

import java.util.List;

public interface ProvinciaRepository {
    List<Provincia> findByDepartamentoId(Long departamentoId);
}