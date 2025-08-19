package com.neill.copagolperu.domain.repository;

import com.neill.copagolperu.domain.model.Academia;
import java.util.List;
import java.util.Optional;

public interface AcademiaRepository {
    Academia save(Academia academia);
    Optional<Academia> findById(Long id);
    List<Academia> findAll();
    void deleteById(Long id);
}

