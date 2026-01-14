package com.neill.copagolperu.shared.infrastructure.repository.ubicacion;

import com.neill.copagolperu.shared.domain.model.ubicacion.Distrito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataDistritoRepository  extends JpaRepository<Distrito, Long> {
    List<Distrito> findByProvinciaId(Long provinciaId);
}