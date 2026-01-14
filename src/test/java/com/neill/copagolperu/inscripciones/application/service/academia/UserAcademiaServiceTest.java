package com.neill.copagolperu.inscripciones.application.service.academia;

import com.neill.copagolperu.shared.infrastructure.exception.AcademiaAlreadyAssignedException;
import com.neill.copagolperu.shared.infrastructure.exception.AcademiaNotFoundException;
import com.neill.copagolperu.shared.infrastructure.exception.UserNotFoundException;
import com.neill.copagolperu.inscripciones.domain.model.Academia;
import com.neill.copagolperu.iam.domain.model.Role;
import com.neill.copagolperu.iam.domain.model.User;
import com.neill.copagolperu.inscripciones.domain.repository.AcademiaRepository;
import com.neill.copagolperu.iam.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserAcademiaServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AcademiaRepository academiaRepository;

    @InjectMocks
    private UserAcademiaService userAcademiaService;

    private UUID userId;
    private UUID academiaId;
    private User user;
    private Academia academia;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
        academiaId = UUID.randomUUID();
        user = new User();
        academia = new Academia();
    }

    @Test
    void asignarAcademiaAUsuario_exitoso() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(academiaRepository.findById(academiaId)).thenReturn(Optional.of(academia));
        when(userRepository.existsByAcademiaId(academiaId)).thenReturn(false);
        when(userRepository.save(user)).thenReturn(user);

        User result = userAcademiaService.asignarAcademiaAUsuario(userId, academiaId);

        assertNotNull(result);
        assertEquals(Role.ACADEMIA, result.getRole());
        assertEquals(academia, result.getAcademia());

        verify(userRepository).save(user);
    }

    @Test
    void asignarAcademiaAUsuario_usuarioNoEncontrado() {
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () ->
                userAcademiaService.asignarAcademiaAUsuario(userId, academiaId)
        );
    }

    @Test
    void asignarAcademiaAUsuario_academiaNoEncontrada() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(academiaRepository.findById(academiaId)).thenReturn(Optional.empty());

        assertThrows(AcademiaNotFoundException.class, () ->
                userAcademiaService.asignarAcademiaAUsuario(userId, academiaId)
        );
    }

    @Test
    void asignarAcademiaAUsuario_academiaYaAsignada() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(academiaRepository.findById(academiaId)).thenReturn(Optional.of(academia));
        when(userRepository.existsByAcademiaId(academiaId)).thenReturn(true);

        assertThrows(AcademiaAlreadyAssignedException.class, () ->
                userAcademiaService.asignarAcademiaAUsuario(userId, academiaId)
        );
    }

    @Test
    void removerAcademiaDeUsuario_exitoso() {
        user.setAcademia(academia);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        User result = userAcademiaService.removerAcademiaDeUsuario(userId);

        assertNull(result.getAcademia());
        verify(userRepository).save(user);
    }
}
