package com.neill.copagolperu.inscripciones.api.controller;

import com.neill.copagolperu.inscripciones.application.dto.request.JugadorRequest;
import com.neill.copagolperu.inscripciones.application.dto.response.JugadorResponse;
import com.neill.copagolperu.inscripciones.application.service.jugador.BuscarJugadorService;
import com.neill.copagolperu.inscripciones.application.service.jugador.EditarJugadorService;
import com.neill.copagolperu.inscripciones.application.service.jugador.ListarJugadoresPorEquipoService;
import com.neill.copagolperu.inscripciones.application.service.jugador.RegistrarJugadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/academias/{academiaId}/equipos/{equipoId}/jugadores")
public class JugadorController {
    private final RegistrarJugadorService registrarJugadorService;
    private final EditarJugadorService editarJugadorService;
    private final BuscarJugadorService buscarJugadorService;
    private final ListarJugadoresPorEquipoService listarJugadoresPorEquipoService;

    public JugadorController(RegistrarJugadorService registrarJugadorService,
                             EditarJugadorService editarJugadorService,
                             BuscarJugadorService buscarJugadorService,
                             ListarJugadoresPorEquipoService listarJugadoresPorEquipoService) {
        this.registrarJugadorService = registrarJugadorService;
        this.editarJugadorService = editarJugadorService;
        this.buscarJugadorService = buscarJugadorService;
        this.listarJugadoresPorEquipoService = listarJugadoresPorEquipoService;
    }

    @PreAuthorize("@academiaSecurity.canAccessAcademy(authentication, #academiaId)")
    @PostMapping
    public ResponseEntity<JugadorResponse> registrarJugador(@PathVariable UUID academiaId, @PathVariable UUID equipoId, @RequestBody JugadorRequest request) {
        JugadorResponse newJugador = registrarJugadorService.registrarJugador(equipoId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newJugador);
    }

    @PreAuthorize("@academiaSecurity.canAccessAcademy(authentication, #academiaId)")
    @PutMapping("/{id}")
    public ResponseEntity<JugadorResponse> editarJugador(@PathVariable UUID academiaId, @PathVariable UUID id, @RequestBody JugadorRequest request) {
        try {
            JugadorResponse updatedJugador = editarJugadorService.editarJugador(id, request);
            return ResponseEntity.status(HttpStatus.OK).body(updatedJugador);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("@academiaSecurity.canAccessAcademy(authentication, #academiaId)")
    @GetMapping("/{id}")
    public ResponseEntity<JugadorResponse> buscarJugador(@PathVariable UUID academiaId, @PathVariable UUID id) {
        Optional<JugadorResponse> jugador = buscarJugadorService.buscarJugador(id);
        return jugador.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("@academiaSecurity.canAccessAcademy(authentication, #academiaId)")
    @GetMapping
    public ResponseEntity<List<JugadorResponse>> listarJugadoresPorEquipo(@PathVariable UUID academiaId, @PathVariable UUID equipoId) {
        return ResponseEntity.ok(listarJugadoresPorEquipoService.listarJugadoresPorEquipo(equipoId));
    }
}