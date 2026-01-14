package com.neill.copagolperu.shared.infrastructure.repository.ubicacion;

import com.neill.copagolperu.shared.domain.model.ubicacion.Distrito;
import com.neill.copagolperu.shared.domain.repository.ubicacion.DistritoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class  JpaDistritoRepository implements DistritoRepository {

    private final SpringDataDistritoRepository jpa;

    public JpaDistritoRepository(SpringDataDistritoRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Optional<Distrito> findById(Long id) {
        return jpa.findById(id);
    }

    @Override
    public List<Distrito> findByProvinciaId(Long provinciaId) {
        return jpa.findByProvinciaId(provinciaId);
    }
}