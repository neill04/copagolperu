package com.neill.copagolperu.infrastructure.repository.ubicacion;

import com.neill.copagolperu.domain.model.ubicacion.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataDepartamentoRepository  extends JpaRepository<Departamento, Long> {}