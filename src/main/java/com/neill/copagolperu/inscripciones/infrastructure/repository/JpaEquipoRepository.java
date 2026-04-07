package com.neill.copagolperu.inscripciones.infrastructure.repository;

import com.neill.copagolperu.inscripciones.domain.model.Categoria;
import com.neill.copagolperu.inscripciones.domain.model.Equipo;
import com.neill.copagolperu.inscripciones.domain.repository.EquipoRepository;
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
    public Optional<Equipo> findByAcademiaIdAndCategoria(UUID idAcademia, Categoria categoria) {
        return jpa.findByAcademiaIdAndCategoria(idAcademia, categoria);
    }

    @Override
    public List<Equipo> findByAcademiaId(UUID idAcademia) {
        return jpa.findByAcademiaId(idAcademia);
    }

    @Override
    public List<Equipo> findAll() {
        return jpa.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        jpa.deleteById(id);
    }

    @Override
    public List<Equipo> findByDelegadoId(UUID id) {
        return jpa.findByDelegadoId(id);
    }

    @Override
    public List<Equipo> findByEntrenadorId(UUID id) { return jpa.findByEntrenadorId(id); }
}