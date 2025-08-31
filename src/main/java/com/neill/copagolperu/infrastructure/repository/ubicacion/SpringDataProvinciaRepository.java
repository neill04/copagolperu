package com.neill.copagolperu.infrastructure.repository.ubicacion;

import com.neill.copagolperu.domain.model.ubicacion.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataProvinciaRepository extends JpaRepository<Provincia, Long> {
    List<Provincia> findByDepartamentoId(Long departamentoId);
}