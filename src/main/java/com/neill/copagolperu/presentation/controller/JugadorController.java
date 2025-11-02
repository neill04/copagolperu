package com.neill.copagolperu.presentation.controller;

import com.neill.copagolperu.application.dto.request.JugadorRequest;
import com.neill.copagolperu.application.dto.response.JugadorResponse;
import com.neill.copagolperu.application.service.academia.jugador.BuscarJugadorService;
import com.neill.copagolperu.application.service.academia.jugador.EditarJugadorService;
import com.neill.copagolperu.application.service.academia.jugador.ListarJugadoresPorEquipoService;
import com.neill.copagolperu.application.service.academia.jugador.RegistrarJugadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<JugadorResponse> registrarJugador(@PathVariable UUID equipoId, @RequestBody JugadorRequest request) {
        JugadorResponse newJugador = registrarJugadorService.registrarJugador(equipoId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newJugador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JugadorResponse> editarJugador(@PathVariable UUID id, @RequestBody JugadorRequest request) {
        try {
            JugadorResponse updatedJugador = editarJugadorService.editarJugador(id, request);
            return ResponseEntity.status(HttpStatus.OK).body(updatedJugador);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<JugadorResponse> buscarJugador(@PathVariable UUID id) {
        Optional<JugadorResponse> jugador = buscarJugadorService.buscarJugador(id);
        return jugador.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<JugadorResponse>> listarJugadoresPorEquipo(@PathVariable UUID equipoId) {
        return ResponseEntity.ok(listarJugadoresPorEquipoService.listarJugadoresPorEquipo(equipoId));
    }
}