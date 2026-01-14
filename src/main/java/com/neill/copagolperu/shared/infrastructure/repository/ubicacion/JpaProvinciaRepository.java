package com.neill.copagolperu.shared.infrastructure.repository.ubicacion;

import com.neill.copagolperu.shared.domain.model.ubicacion.Provincia;
import com.neill.copagolperu.shared.domain.repository.ubicacion.ProvinciaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaProvinciaRepository implements ProvinciaRepository {

    private final SpringDataProvinciaRepository jpa;

    public JpaProvinciaRepository(SpringDataProvinciaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public List<Provincia> findByDepartamentoId(Long departamentoId) {
        return jpa.findByDepartamentoId(departamentoId);
    }
}