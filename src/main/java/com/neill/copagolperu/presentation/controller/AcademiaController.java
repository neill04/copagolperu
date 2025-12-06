package com.neill.copagolperu.presentation.controller;

import com.neill.copagolperu.application.dto.request.AcademiaRequest;
import com.neill.copagolperu.application.dto.response.AcademiaResponse;
import com.neill.copagolperu.application.dto.response.JugadorResponse;
import com.neill.copagolperu.application.service.academia.jugador.ListarTodosLosJugadoresPorAcademiaService;
import com.neill.copagolperu.application.service.admin.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/academias")
public class AcademiaController {
    private final RegistrarAcademiaService registrarAcademiaService;
    private final EditarAcademiaService editarAcademiaService;
    private final BuscarAcademiaService buscarAcademiaService;
    private final ListarTodosLosJugadoresPorAcademiaService listarTodosLosJugadoresPorAcademiaService;

    public AcademiaController(
            RegistrarAcademiaService registrarAcademiaService,
            EditarAcademiaService editarAcademiaService,
            BuscarAcademiaService buscarAcademiaService,
            ListarTodosLosJugadoresPorAcademiaService listarTodosLosJugadoresPorAcademiaService) {
        this.registrarAcademiaService = registrarAcademiaService;
        this.editarAcademiaService = editarAcademiaService;
        this.buscarAcademiaService = buscarAcademiaService;
        this.listarTodosLosJugadoresPorAcademiaService = listarTodosLosJugadoresPorAcademiaService;
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @PostMapping
    public ResponseEntity<AcademiaResponse> registrarAcademia(@RequestBody AcademiaRequest request) {
        AcademiaResponse newAcademia = registrarAcademiaService.registrarAcademia(request);
        return ResponseEntity.ok(newAcademia);
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @PutMapping("/{id}")
    public ResponseEntity<AcademiaResponse> editarAcademia(@PathVariable UUID id, @RequestBody AcademiaRequest request) {
        try {
            AcademiaResponse updatedAcademia = editarAcademiaService.editarAcademia(id, request);
            return ResponseEntity.ok(updatedAcademia);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("@academiaSecurity.canAccessAcademy(authentication, #id)")
    @GetMapping("/{id}")
    public ResponseEntity<AcademiaResponse> buscarAcademia(@PathVariable UUID id) {
        Optional<AcademiaResponse> academia = buscarAcademiaService.buscarAcademia(id);
        return academia.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("@academiaSecurity.canAccessAcademy(authentication, #id)")
    @GetMapping("/{id}/jugadores")
    public ResponseEntity<List<JugadorResponse>> listarTodosLosJugadoresPorAcademia(@PathVariable UUID id) {
        return ResponseEntity.ok(listarTodosLosJugadoresPorAcademiaService.listarTodosLosJugadoresPorAcademia(id));
    }
}