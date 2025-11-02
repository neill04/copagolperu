package com.neill.copagolperu.presentation.controller;

import com.neill.copagolperu.application.dto.request.AcademiaRequest;
import com.neill.copagolperu.application.dto.response.AcademiaResponse;
import com.neill.copagolperu.application.service.admin.*;
import org.springframework.http.ResponseEntity;
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
    private final ListarAcademiasService listarAcademiasService;
    private final MostrarTotalEquiposPorAcademiaService mostrarTotalEquiposPorAcademiaService;

    public AcademiaController(
            RegistrarAcademiaService registrarAcademiaService,
            EditarAcademiaService editarAcademiaService,
            BuscarAcademiaService buscarAcademiaService,
            ListarAcademiasService listarAcademiasService,
            MostrarTotalEquiposPorAcademiaService mostrarTotalEquiposPorAcademiaService) {
        this.registrarAcademiaService = registrarAcademiaService;
        this.editarAcademiaService = editarAcademiaService;
        this.buscarAcademiaService = buscarAcademiaService;
        this.listarAcademiasService = listarAcademiasService;
        this.mostrarTotalEquiposPorAcademiaService = mostrarTotalEquiposPorAcademiaService;
    }

    @PostMapping
    public ResponseEntity<AcademiaResponse> registrarAcademia(@RequestBody AcademiaRequest request) {
        AcademiaResponse newAcademia = registrarAcademiaService.registrarAcademia(request);
        return ResponseEntity.ok(newAcademia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AcademiaResponse> editarAcademia(@PathVariable UUID id, @RequestBody AcademiaRequest request) {
        try {
            AcademiaResponse updatedAcademia = editarAcademiaService.editarAcademia(id, request);
            return ResponseEntity.ok(updatedAcademia);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcademiaResponse> buscarAcademia(@PathVariable UUID id) {
        Optional<AcademiaResponse> academia = buscarAcademiaService.buscarAcademia(id);
        return academia.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AcademiaResponse>> listarAcademias() {
        return ResponseEntity.ok(listarAcademiasService.listarAcademias());
    }

    @GetMapping("/totalEquipos/{id}")
    public long mostrarTotalEquiposPorAcademia(@PathVariable UUID id) {
        long totalTeams = mostrarTotalEquiposPorAcademiaService.mostrarTotalEquiposPorAcademia(id);
        return totalTeams;
    }
}