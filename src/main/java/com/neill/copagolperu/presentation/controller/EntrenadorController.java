package com.neill.copagolperu.presentation.controller;

import com.neill.copagolperu.application.dto.request.EntrenadorRequest;
import com.neill.copagolperu.application.dto.response.EntrenadorResponse;
import com.neill.copagolperu.application.service.academia.entrenador.BuscarEntrenadorService;
import com.neill.copagolperu.application.service.academia.entrenador.EditarEntrenadorService;
import com.neill.copagolperu.application.service.academia.entrenador.ListarEntrenadoresPorAcademiaService;
import com.neill.copagolperu.application.service.academia.entrenador.RegistrarEntrenadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/academias/{academiaId}/entrenadores")
public class EntrenadorController {
    private final RegistrarEntrenadorService registrarEntrenadorService;
    private final EditarEntrenadorService editarEntrenadorService;
    private final BuscarEntrenadorService buscarEntrenadorService;
    private final ListarEntrenadoresPorAcademiaService listarEntrenadoresPorAcademiaService;

    public EntrenadorController(RegistrarEntrenadorService registrarEntrenadorService,
                                EditarEntrenadorService editarEntrenadorService,
                                BuscarEntrenadorService buscarEntrenadorService,
                                ListarEntrenadoresPorAcademiaService listarEntrenadoresPorAcademiaService) {
        this.registrarEntrenadorService = registrarEntrenadorService;
        this.editarEntrenadorService = editarEntrenadorService;
        this.buscarEntrenadorService = buscarEntrenadorService;
        this.listarEntrenadoresPorAcademiaService = listarEntrenadoresPorAcademiaService;
    }

    @PostMapping
    public ResponseEntity<EntrenadorResponse> registrarEntrenador(@PathVariable UUID academiaId, @RequestBody EntrenadorRequest request) {
        EntrenadorResponse newEntrenador = registrarEntrenadorService.registrarEntrenador(academiaId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEntrenador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntrenadorResponse> editarEntrenador(@PathVariable UUID id, @RequestBody EntrenadorRequest request) {
        try {
            EntrenadorResponse updatedEntrenador = editarEntrenadorService.editarEntrenador(id, request);
            return ResponseEntity.status(HttpStatus.OK).body(updatedEntrenador);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntrenadorResponse> buscarEntrenador(@PathVariable UUID id) {
        Optional<EntrenadorResponse> entrenador = buscarEntrenadorService.buscarEntrenador(id);
        return entrenador.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EntrenadorResponse>> listarEntrenadoresPorAcademia(@PathVariable UUID academiaId) {
        return ResponseEntity.ok(listarEntrenadoresPorAcademiaService.listarEntrenadoresPorAcademia(academiaId));
    }
}