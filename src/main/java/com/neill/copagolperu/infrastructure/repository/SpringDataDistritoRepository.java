package com.neill.copagolperu.infrastructure.repository;

import com.neill.copagolperu.domain.model.ubicacion.Distrito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataDistritoRepository  extends JpaRepository<Distrito, Long> {}