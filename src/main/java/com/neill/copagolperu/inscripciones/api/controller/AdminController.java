package com.neill.copagolperu.inscripciones.api.controller;

import com.neill.copagolperu.iam.application.dto.response.UserInfoDTO;
import com.neill.copagolperu.inscripciones.application.dto.response.*;
import com.neill.copagolperu.inscripciones.application.service.delegado.ListarTodosLosDelegadosService;
import com.neill.copagolperu.inscripciones.application.service.entrenador.ListarTodosLosEntrenadoresService;
import com.neill.copagolperu.inscripciones.application.service.equipo.ListarTodosLosEquiposService;
import com.neill.copagolperu.inscripciones.application.service.jugador.ListarTodosLosJugadoresService;
import com.neill.copagolperu.inscripciones.application.service.academia.ListarTodasLasAcademiasService;
import com.neill.copagolperu.inscripciones.application.service.academia.ListarTodosLosUsuariosService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminController {
    private final ListarTodasLasAcademiasService listarTodasLasAcademiasService;
    private final ListarTodosLosEquiposService listarTodosLosEquiposService;
    private final ListarTodosLosEntrenadoresService listarTodosLosEntrenadoresService;
    private final ListarTodosLosDelegadosService listarTodosLosDelegadosService;
    private final ListarTodosLosJugadoresService listarTodosLosJugadoresService;
    private final ListarTodosLosUsuariosService listarTodosLosUsuariosService;

    public AdminController(ListarTodosLosEquiposService listarTodosLosEquiposService,
                           ListarTodosLosEntrenadoresService listarTodosLosEntrenadoresService,
                           ListarTodosLosDelegadosService listarTodosLosDelegadosService,
                           ListarTodosLosJugadoresService listarTodosLosJugadoresService,
                           ListarTodasLasAcademiasService listarTodasLasAcademiasService,
                           ListarTodosLosUsuariosService listarTodosLosUsuariosService) {
        this.listarTodosLosEquiposService = listarTodosLosEquiposService;
        this.listarTodosLosEntrenadoresService = listarTodosLosEntrenadoresService;
        this.listarTodosLosDelegadosService = listarTodosLosDelegadosService;
        this.listarTodosLosJugadoresService = listarTodosLosJugadoresService;
        this.listarTodasLasAcademiasService = listarTodasLasAcademiasService;
        this.listarTodosLosUsuariosService = listarTodosLosUsuariosService;
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @GetMapping("/academias")
    public ResponseEntity<List<AcademiaResponse>> listarTodasLasAcademias() {
        return ResponseEntity.ok(listarTodasLasAcademiasService.listarTodasLasAcademias());
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @GetMapping("/equipos")
    public ResponseEntity<List<EquipoResponse>> listarTodosLosEquipos() {
        return ResponseEntity.ok(listarTodosLosEquiposService.listarTodosLosEquipos());
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @GetMapping("/entrenadores")
    public ResponseEntity<List<EntrenadorResponse>> listarTodosLosEntrenadores() {
        return ResponseEntity.ok(listarTodosLosEntrenadoresService.listarTodosLosEntrenadores());
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @GetMapping("/delegados")
    public ResponseEntity<List<DelegadoResponse>> listarTodosLosDelegados() {
        return ResponseEntity.ok(listarTodosLosDelegadosService.listarTodosLosDelegados());
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @GetMapping("/jugadores")
    public ResponseEntity<List<JugadorResponse>> listarTodosLosJugadores() {
        return ResponseEntity.ok(listarTodosLosJugadoresService.listarTodosLosJugadores());
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @GetMapping("/usuarios")
    public ResponseEntity<List<UserInfoDTO>> listarTodosLosUsuarios() {
        return ResponseEntity.ok(listarTodosLosUsuariosService.listarTodosLosUsuarios());
    }
}