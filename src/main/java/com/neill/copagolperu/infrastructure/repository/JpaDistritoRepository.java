package com.neill.copagolperu.infrastructure.repository;

import com.neill.copagolperu.domain.model.ubicacion.Distrito;
import com.neill.copagolperu.domain.repository.DistritoRepository;
import org.springframework.stereotype.Repository;

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
}