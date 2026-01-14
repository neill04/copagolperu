package com.neill.copagolperu.inscripciones.api.controller;

import com.neill.copagolperu.inscripciones.application.dto.request.EntrenadorRequest;
import com.neill.copagolperu.inscripciones.application.dto.response.EntrenadorResponse;
import com.neill.copagolperu.inscripciones.application.service.entrenador.BuscarEntrenadorService;
import com.neill.copagolperu.inscripciones.application.service.entrenador.EditarEntrenadorService;
import com.neill.copagolperu.inscripciones.application.service.entrenador.ListarEntrenadoresPorAcademiaService;
import com.neill.copagolperu.inscripciones.application.service.entrenador.RegistrarEntrenadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("@academiaSecurity.canAccessAcademy(authentication, #academiaId)")
    @PostMapping
    public ResponseEntity<EntrenadorResponse> registrarEntrenador(@PathVariable UUID academiaId, @RequestBody EntrenadorRequest request) {
        EntrenadorResponse newEntrenador = registrarEntrenadorService.registrarEntrenador(academiaId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEntrenador);
    }

    @PreAuthorize("@academiaSecurity.canAccessAcademy(authentication, #academiaId)")
    @PutMapping("/{id}")
    public ResponseEntity<EntrenadorResponse> editarEntrenador(@PathVariable UUID academiaId, @PathVariable UUID id, @RequestBody EntrenadorRequest request) {
        try {
            EntrenadorResponse updatedEntrenador = editarEntrenadorService.editarEntrenador(id, request);
            return ResponseEntity.status(HttpStatus.OK).body(updatedEntrenador);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("@academiaSecurity.canAccessAcademy(authentication, #academiaId)")
    @GetMapping("/{id}")
    public ResponseEntity<EntrenadorResponse> buscarEntrenador(@PathVariable UUID academiaId, @PathVariable UUID id) {
        Optional<EntrenadorResponse> entrenador = buscarEntrenadorService.buscarEntrenador(id);
        return entrenador.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("@academiaSecurity.canAccessAcademy(authentication, #academiaId)")
    @GetMapping
    public ResponseEntity<List<EntrenadorResponse>> listarEntrenadoresPorAcademia(@PathVariable UUID academiaId) {
        return ResponseEntity.ok(listarEntrenadoresPorAcademiaService.listarEntrenadoresPorAcademia(academiaId));
    }
}