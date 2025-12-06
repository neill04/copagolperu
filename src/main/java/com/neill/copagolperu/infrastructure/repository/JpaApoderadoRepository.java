/*
package com.neill.copagolperu.infrastructure.repository;

import com.neill.copagolperu.domain.model.Apoderado;
import com.neill.copagolperu.domain.repository.ApoderadoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaApoderadoRepository implements ApoderadoRepository {

    private final SpringDataApoderadoRepository jpa;

    public JpaApoderadoRepository(SpringDataApoderadoRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Apoderado save(Apoderado apoderado) {
        return jpa.save(apoderado);
    }

    @Override
    public Optional<Apoderado> findById(UUID id) {
        return jpa.findById(id);
    }

    @Override
    public List<Apoderado> findDistinctByEquipoId(UUID equipoId) {
        return jpa.findDistinctByJugadores_Equipo_Id(equipoId);
    }

    @Override
    public Optional<Apoderado> findByDni(String dni) {
        return jpa.findByDni(dni);
    }

    @Override
    public void deleteById(UUID id) {
        jpa.deleteById(id);
    }
}
*/