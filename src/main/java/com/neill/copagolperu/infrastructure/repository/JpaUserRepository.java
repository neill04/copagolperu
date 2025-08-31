package com.neill.copagolperu.infrastructure.repository;

import com.neill.copagolperu.domain.model.User;
import com.neill.copagolperu.domain.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaUserRepository extends JpaRepository<User, UUID>, UserRepository {}