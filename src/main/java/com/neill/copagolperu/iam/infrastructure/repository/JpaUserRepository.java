package com.neill.copagolperu.iam.infrastructure.repository;

import com.neill.copagolperu.iam.domain.model.User;
import com.neill.copagolperu.iam.domain.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaUserRepository extends JpaRepository<User, UUID>, UserRepository {}