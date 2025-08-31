package com.neill.copagolperu.domain.repository.ubicacion;

import com.neill.copagolperu.domain.model.ubicacion.Provincia;

import java.util.List;

public interface ProvinciaRepository {
    List<Provincia> findByDepartamentoId(Long departamentoId);
}