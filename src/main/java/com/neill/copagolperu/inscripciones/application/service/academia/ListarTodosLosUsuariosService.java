package com.neill.copagolperu.inscripciones.application.service.academia;

import com.neill.copagolperu.iam.application.dto.response.UserInfoDTO;
import com.neill.copagolperu.iam.application.mapper.AuthMapper;
import com.neill.copagolperu.iam.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarTodosLosUsuariosService {

    private final UserRepository userRepository;

    public ListarTodosLosUsuariosService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserInfoDTO> listarTodosLosUsuarios() {
        return userRepository.findAll()
                .stream()
                .map(AuthMapper::toDTO)
                .toList();
    }
}
