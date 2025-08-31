package com.neill.copagolperu.application.service;

import com.neill.copagolperu.application.dto.CreateUserDTO;
import com.neill.copagolperu.application.dto.LoginRequestDTO;
import com.neill.copagolperu.application.mapper.AuthMapper;
import com.neill.copagolperu.domain.model.User;
import com.neill.copagolperu.domain.repository.UserRepository;
import com.neill.copagolperu.domain.service.TokenService_;
import com.neill.copagolperu.domain.service.AuthService;
import com.neill.copagolperu.infrastructure.exceptions.FincasErrorMessage;
import com.neill.copagolperu.infrastructure.exceptions.FincasException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService, UserDetailsService {
    private static final Logger logger = LogManager.getLogger(AuthServiceImpl.class);

    private final UserRepository userRepository;
    private final TokenService_ tokenService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationConfiguration authenticationConfiguration;

    public AuthServiceImpl(
            UserRepository userRepository,
            TokenService_ tokenService,
            PasswordEncoder passwordEncoder,
            AuthenticationConfiguration authenticationConfiguration
    ) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Override
    public void createUser(final CreateUserDTO createUserDTO) {
        final User createUser = AuthMapper.fromDTO(createUserDTO);
        createUser.setPassword(passwordEncoder.encode(createUserDTO.password()));
        final User user = userRepository.save(createUser);
        logger.info("[USER] : User successfully created");
    }

    @Override
    public User getUser(final UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("[USER] : User not found with id {}", id);
                    return new FincasException(FincasErrorMessage.USER_NOT_FOUND);
                });
    }

    @Override
    public String login(final LoginRequestDTO loginRequest) {
        try {
            final AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
            final Authentication authRequest = AuthMapper.fromDTO(loginRequest);
            final Authentication authentication = authenticationManager.authenticate(authRequest);
            return tokenService.generateToken(authentication);
        } catch (Exception e) {
            logger.error("[USER] : Error while trying to login", e);
            throw new ProviderNotFoundException("Error while trying to login");
        }
    }

    @Override
    public boolean validateToken(final String token) {
        return tokenService.validateToken(token);
    }

    @Override
    public String getUserFromToken(final String token) {
        return tokenService.getUserFromToken(token);
    }

    @Override
    public UserDetails loadUserByUsername(final String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> {
                    logger.error("[USER] : User not found with email {}", username);
                    return new UsernameNotFoundException("User not found");
                });
    }
}