package com.neill.copagolperu.infrastructure.filters;

import com.neill.copagolperu.application.AuthCookieConstants;
import com.neill.copagolperu.domain.service.AuthService;
//import com.neill.copagolperu.infrastructure.configuration.SecurityConfig;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final AuthService authService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(AuthService authService, UserDetailsService userDetailsService) {
        this.authService = authService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.equals("/api/auth") || path.equals("/api/auth/login");
        /*
        final String requestURI = request.getRequestURI();
        return requestURI.equals(SecurityConfig.LOGIN_URL_MATCHER);
         */
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {

        final Optional<String> token = getJwtFromCookie(request);
        System.out.println("Token en cookie: " + token.orElse("NO TOKEN"));

        if (token.isEmpty() || !authService.validateToken(token.get())) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String userName = authService.getUserFromToken(token.get());
        System.out.println("Username extraído del token: " + userName);

        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        System.out.println("UserDetails encontrado: " + (userDetails != null));

        if (userDetails == null) {
            System.out.println("No se encontró el usuario, devolviendo 401...");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(userDetails);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // Log temporal
        System.out.println("Usuario autenticado: " + userDetails.getUsername());
        userDetails.getAuthorities().forEach(a ->
                System.out.println("Authority: " + a.getAuthority())
        );

        filterChain.doFilter(request, response);
    }

    private Optional<String> getJwtFromCookie(HttpServletRequest request) {
        final Cookie[] cookies = request.getCookies();
        if (cookies == null || ArrayUtils.isEmpty(cookies)) {
            return Optional.empty();
        }
        return (Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(AuthCookieConstants.TOKEN_COOKIE_NAME))
                .map(Cookie::getValue)
                .findFirst());
    }
}