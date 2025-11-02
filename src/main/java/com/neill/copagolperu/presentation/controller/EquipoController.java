package com.neill.copagolperu.presentation.controller;

import com.neill.copagolperu.application.dto.request.EquipoRequest;
import com.neill.copagolperu.application.dto.response.EquipoResponse;
import com.neill.copagolperu.application.service.academia.equipo.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/academias/{academiaId}/equipos")
public class EquipoController {
    private final RegistrarEquipoService registrarEquipoService;
    private final EditarEquipoService editarEquipoService;
    private final BuscarEquipoService buscarEquipoService;
    private final ListarEquiposPorAcademiaService listarEquiposPorAcademiaService;
    private final GenerarPlanillaService generarPlanillaService;

    public EquipoController(RegistrarEquipoService registrarEquipoService,
                            EditarEquipoService editarEquipoService,
                            BuscarEquipoService buscarEquipoService,
                            ListarEquiposPorAcademiaService listarEquiposPorAcademiaService, GenerarPlanillaService generarPlanillaService) {
        this.registrarEquipoService = registrarEquipoService;
        this.editarEquipoService = editarEquipoService;
        this.buscarEquipoService = buscarEquipoService;
        this.listarEquiposPorAcademiaService = listarEquiposPorAcademiaService;
        this.generarPlanillaService = generarPlanillaService;
    }

    @PostMapping
    public ResponseEntity<EquipoResponse> registrarEquipo(@PathVariable UUID academiaId, @Valid @RequestBody EquipoRequest request) {
        EquipoResponse newEquipo = registrarEquipoService.registrarEquipo(academiaId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEquipo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipoResponse> editarEquipo(@PathVariable UUID id, @Valid @RequestBody EquipoRequest request) {
        try {
            EquipoResponse updatedEquipo = editarEquipoService.editarEquipo(id, request);
            return ResponseEntity.ok(updatedEquipo);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipoResponse> buscarEquipo(@PathVariable UUID id) {
        Optional<EquipoResponse> equipo = buscarEquipoService.buscarEquipo(id);
        return equipo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EquipoResponse>> listarEquiposPorAcademia(@PathVariable UUID academiaId) {
        return ResponseEntity.ok(listarEquiposPorAcademiaService.listarEquiposPorAcademia(academiaId));
    }

    @GetMapping("/{id}/planilla")
    public ResponseEntity<byte[]> exportarPlanilla(@PathVariable UUID id) throws IOException {
        byte[] excelBytes = generarPlanillaService.generarPlanillaExcel(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"planilla_equipo.xlsx\"")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(excelBytes);
    }
}