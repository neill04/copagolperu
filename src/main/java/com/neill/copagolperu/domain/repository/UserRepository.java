package com.neill.copagolperu.domain.repository;

import com.neill.copagolperu.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
    User save(User user);
    Optional<User> findById(UUID id);
    boolean existsByAcademiaId(UUID academiaId);
    Optional<User> findByAcademiaId(UUID academiaId);
}