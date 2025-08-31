package com.neill.copagolperu.domain.repository.ubicacion;

import com.neill.copagolperu.domain.model.ubicacion.Departamento;

import java.util.List;

public interface DepartamentoRepository {
    List<Departamento> findAll();
}