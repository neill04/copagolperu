package com.neill.copagolperu.infrastructure.repository;

import com.neill.copagolperu.domain.model.Delegado;
import com.neill.copagolperu.domain.repository.DelegadoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaDelegadoRepository implements DelegadoRepository {

    private final SpringDataDelegadoRepository jpa;

    public JpaDelegadoRepository(SpringDataDelegadoRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Delegado save(Delegado delegado) {
        return jpa.save(delegado);
    }

    @Override
    public Optional<Delegado> findById(UUID id) {
        return jpa.findById(id);
    }

    @Override
    public List<Delegado> findByAcademiaId(UUID idAcademia) {
        return jpa.findByAcademiaId(idAcademia);
    }
}