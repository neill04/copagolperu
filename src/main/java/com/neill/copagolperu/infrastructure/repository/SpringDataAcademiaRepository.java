package com.neill.copagolperu.infrastructure.repository;

import com.neill.copagolperu.domain.model.Academia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataAcademiaRepository extends JpaRepository<Academia, Long> {}