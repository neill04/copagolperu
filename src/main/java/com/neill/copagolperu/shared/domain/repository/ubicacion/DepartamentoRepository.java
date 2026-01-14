package com.neill.copagolperu.shared.domain.repository.ubicacion;

import com.neill.copagolperu.shared.domain.model.ubicacion.Departamento;

import java.util.List;

public interface DepartamentoRepository {
    List<Departamento> findAll();
}