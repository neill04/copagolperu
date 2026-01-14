package com.neill.copagolperu.shared.infrastructure.repository.ubicacion;

import com.neill.copagolperu.shared.domain.model.ubicacion.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataDepartamentoRepository  extends JpaRepository<Departamento, Long> {}