package com.neill.copagolperu.application.service.admin;

import com.neill.copagolperu.application.exception.AcademiaAlreadyAssignedException;
import com.neill.copagolperu.application.exception.AcademiaNotFoundException;
import com.neill.copagolperu.application.exception.UserNotFoundException;
import com.neill.copagolperu.domain.model.Academia;
import com.neill.copagolperu.domain.model.Role;
import com.neill.copagolperu.domain.model.User;
import com.neill.copagolperu.domain.repository.AcademiaRepository;
import com.neill.copagolperu.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserAcademiaService {
    private final UserRepository userRepository;
    private final AcademiaRepository academiaRepository;

    public UserAcademiaService(UserRepository userRepository, AcademiaRepository academiaRepository) {
        this.userRepository = userRepository;
        this.academiaRepository = academiaRepository;
    }

    @Transactional
    public User asignarAcademiaAUsuario(UUID userId, UUID academiaId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

        Academia academia = academiaRepository.findById(academiaId)
                .orElseThrow(() -> new AcademiaNotFoundException("Academia no encontrada"));

        if (userRepository.existsByAcademiaId(academiaId)) {
            throw new AcademiaAlreadyAssignedException("Esta academia ya tiene un encargado asignado.");
        }

        user.setAcademia(academia);
        user.setRole(Role.ACADEMIA);

        return userRepository.save(user);
    }

    @Transactional
    public User removerAcademiaDeUsuario(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

        user.setAcademia(null);

        return userRepository.save(user);
    }
}