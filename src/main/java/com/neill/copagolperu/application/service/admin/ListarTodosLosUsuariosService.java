package com.neill.copagolperu.application.service.admin;

import com.neill.copagolperu.application.dto.UserInfoDTO;
import com.neill.copagolperu.application.mapper.AuthMapper;
import com.neill.copagolperu.domain.repository.UserRepository;
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
