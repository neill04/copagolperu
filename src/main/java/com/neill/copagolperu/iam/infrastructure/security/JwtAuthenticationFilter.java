package com.neill.copagolperu.iam.infrastructure.security;

import com.neill.copagolperu.iam.application.service.TokenService_;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final TokenService_ tokenService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(TokenService_ tokenService,
                                   UserDetailsService userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            final String authHeader = request.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            // Extraer token (quitar "Bearer ")
            final String jwt = authHeader.substring(7);

            logger.debug("[JWT] Token extraído del header Authorization");

            // Validar token
            if (!tokenService.validateToken(jwt)) {
                logger.warn("[JWT] Token inválido o expirado");
                filterChain.doFilter(request, response);
                return;
            }

            // Extraer username del token
            final String username = tokenService.getUserFromToken(jwt);
            logger.debug("[JWT] Username extraído del token: {}", username);

            // Si ya está autenticado, no hacer nada
            if (SecurityContextHolder.getContext().getAuthentication() != null) {
                filterChain.doFilter(request, response);
                return;
            }

            // Cargar usuario desde la base de datos
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            logger.debug("[JWT] UserDetails cargado: {}", userDetails.getUsername());

            // Crear authentication token
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // Establecer autenticación en el contexto
            SecurityContextHolder.getContext().setAuthentication(authToken);

            logger.debug("[JWT] Usuario autenticado: {} con rol: {}",
                    username,
                    userDetails.getAuthorities()
            );

        } catch (Exception e) {
            logger.error("[JWT] Error procesando token", e);
        }

        filterChain.doFilter(request, response);
    }
}