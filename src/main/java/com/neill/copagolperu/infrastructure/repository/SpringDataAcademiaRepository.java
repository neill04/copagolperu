package com.neill.copagolperu.infrastructure.repository;

import com.neill.copagolperu.domain.model.Academia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataAcademiaRepository extends JpaRepository<Academia, UUID> {}