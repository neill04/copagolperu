package com.neill.copagolperu.infrastructure.repository;

import com.neill.copagolperu.domain.model.Equipo;
import com.neill.copagolperu.domain.repository.EquipoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaEquipoRepository implements EquipoRepository {

    private final SpringDataEquipoRepository jpa;

    public JpaEquipoRepository(SpringDataEquipoRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Equipo save(Equipo equipo) {
        return jpa.save(equipo);
    }

    @Override
    public Optional<Equipo> findById(UUID id) {
        return jpa.findById(id);
    }

    @Override
    public List<Equipo> findByAcademiaId(UUID idAcademia) {
        return jpa.findByAcademiaId(idAcademia);
    }

    @Override
    public List<Equipo> findAll() {
        return jpa.findAll();
    }
}