package com.neill.copagolperu.application.service;

import com.neill.copagolperu.application.dto.request.LoginRequest;
import com.neill.copagolperu.application.dto.request.UserRequest;
import com.neill.copagolperu.application.dto.response.LoginResponse;
import com.neill.copagolperu.application.mapper.AuthMapper;
import com.neill.copagolperu.application.mapper.UserMapper;
import com.neill.copagolperu.domain.model.User;
import com.neill.copagolperu.domain.repository.UserRepository;
import com.neill.copagolperu.domain.service.TokenService_;
import com.neill.copagolperu.domain.service.AuthService;
import com.neill.copagolperu.infrastructure.exceptions.FincasErrorMessage;
import com.neill.copagolperu.infrastructure.exceptions.FincasException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService, UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Value("${application.security.jwt.expiration:7200}")
    private long jwtExpiration;

    private final UserRepository userRepository;
    private final TokenService_ tokenService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationConfiguration authenticationConfiguration;

    public AuthServiceImpl(
            UserRepository userRepository,
            TokenService_ tokenService,
            PasswordEncoder passwordEncoder,
            @Lazy AuthenticationConfiguration authenticationConfiguration
    ) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Override
    public void createUser(final UserRequest request) {
        final User createUser = AuthMapper.fromDTO(request);
        createUser.setPassword(passwordEncoder.encode(request.password()));
        final User user = userRepository.save(createUser);
        logger.info("[USER] : User successfully created: {}", user.getUsername());
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
    public LoginResponse login(final LoginRequest loginRequest) {
        try {
            logger.info("[AUTH] Login attempt for user: {}", loginRequest.username());

            final AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
            final Authentication authRequest = AuthMapper.fromDTO(loginRequest);
            final Authentication authentication = authenticationManager.authenticate(authRequest);

            String token = tokenService.generateToken(authentication);

            User user = (User) authentication.getPrincipal();

            logger.info("[AUTH] Login succesful for user: {}", user.getUsername());

            return new LoginResponse(
                    token,
                    "Bearer",
                    jwtExpiration,
                    UserMapper.toUserInfo(user)
            );

        } catch (Exception e) {
            logger.error("[USER] : Error during login for user: {}", loginRequest.username(), e);
            throw new ProviderNotFoundException("Invalid credentials");
        }
    }

    @Override
    public LoginResponse getUserInfo(String username) {
        logger.debug("[AUTH] Getting user info for: {}", username);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    logger.error("[AUTH] User not found with username: {}", username);
                    return new UsernameNotFoundException("User not found with username: " + username);
                });

        return UserMapper.toResponse(user);
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
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    logger.error("[USER] User not found with username: {}", username);
                    return new UsernameNotFoundException("User not found");
                });
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("[AUTH] Loading user by username: {}", username);

        return userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    logger.error("[USER] : User not found with username {}", username);
                    return new UsernameNotFoundException("User not found");
                });
    }
}