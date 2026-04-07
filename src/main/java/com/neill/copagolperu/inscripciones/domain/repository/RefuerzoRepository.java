package com.neill.copagolperu.inscripciones.domain.repository;

import com.neill.copagolperu.inscripciones.domain.model.Refuerzo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RefuerzoRepository extends JpaRepository<Refuerzo, UUID> {
    List<Refuerzo> findByEquipoDestinoId(UUID equipoId);
    long countByEquipoDestinoId(UUID equipoDestinoId);
    boolean existsByJugadorId(UUID jugadorId);
    void deleteByJugadorIdAndEquipoDestinoId(UUID jugadorId, UUID equipoDestinoId);
    void deleteByJugadorEquipoId(UUID equipoId);
    void deleteByJugadorId(UUID jugadorId);
}
