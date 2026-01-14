package com.neill.copagolperu.shared.infrastructure.repository.ubicacion;

import com.neill.copagolperu.shared.domain.model.ubicacion.Departamento;
import com.neill.copagolperu.shared.domain.repository.ubicacion.DepartamentoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaDepartamentoRepository implements DepartamentoRepository {

    private final SpringDataDepartamentoRepository jpa;

    public JpaDepartamentoRepository(SpringDataDepartamentoRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public List<Departamento> findAll() {
        return jpa.findAll();
    }
}