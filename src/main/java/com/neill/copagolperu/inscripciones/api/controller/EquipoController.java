package com.neill.copagolperu.inscripciones.api.controller;

import com.neill.copagolperu.inscripciones.application.dto.request.EquipoRequest;
import com.neill.copagolperu.inscripciones.application.dto.response.EquipoResponse;
import com.neill.copagolperu.inscripciones.application.service.equipo.*;
import com.neill.copagolperu.inscripciones.application.service.jugador.BuscarJugadorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/academias/{academiaId}/equipos")
public class EquipoController {
    private final RegistrarEquipoService registrarEquipoService;
    private final EditarEquipoService editarEquipoService;
    private final RefuerzoService refuerzoService;
    private final EliminarRefuerzoService eliminarRefuerzoService;
    private final BuscarEquipoService buscarEquipoService;
    private final ListarEquiposPorAcademiaService listarEquiposPorAcademiaService;
    private final GenerarPlanillaService generarPlanillaService;

    @PreAuthorize("@academiaSecurity.canAccessAcademy(authentication, #academiaId)")
    @PostMapping
    public ResponseEntity<EquipoResponse> registrarEquipo(@PathVariable UUID academiaId, @Valid @RequestBody EquipoRequest request) {
        EquipoResponse newEquipo = registrarEquipoService.registrarEquipo(academiaId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEquipo);
    }

    @PreAuthorize("@academiaSecurity.canAccessAcademy(authentication, #academiaId)")
    @PostMapping("/{equipoDestinoId}/refuerzos/{jugadorId}")
    public ResponseEntity<?> registrarRefuerzo(@PathVariable UUID academiaId, @PathVariable UUID equipoDestinoId, @PathVariable UUID jugadorId) {
        refuerzoService.registrarRefuerzo(jugadorId, equipoDestinoId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Jugador registrado como refuerzo exitosamente.");
    }

    @PreAuthorize("@academiaSecurity.canAccessAcademy(authentication, #academiaId)")
    @DeleteMapping("/{equipoDestinoId}/refuerzos/{jugadorId}")
    public ResponseEntity<Void> eliminarRefuerzo(@PathVariable UUID academiaId, @PathVariable UUID equipoDestinoId, @PathVariable UUID jugadorId) {
        eliminarRefuerzoService.eliminarRefuerzo(equipoDestinoId, jugadorId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("@academiaSecurity.canAccessAcademy(authentication, #academiaId)")
    @PutMapping("/{id}")
    public ResponseEntity<EquipoResponse> editarEquipo(@PathVariable UUID academiaId, @PathVariable UUID id, @Valid @RequestBody EquipoRequest request) {
        try {
            EquipoResponse updatedEquipo = editarEquipoService.editarEquipo(id, request);
            return ResponseEntity.ok(updatedEquipo);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("@academiaSecurity.canAccessAcademy(authentication, #academiaId)")
    @GetMapping("/{id}")
    public ResponseEntity<EquipoResponse> buscarEquipo(@PathVariable UUID academiaId, @PathVariable UUID id) {
        Optional<EquipoResponse> equipo = buscarEquipoService.buscarEquipo(id);
        return equipo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("@academiaSecurity.canAccessAcademy(authentication, #academiaId)")
    @GetMapping
    public ResponseEntity<List<EquipoResponse>> listarEquiposPorAcademia(@PathVariable UUID academiaId) {
        return ResponseEntity.ok(listarEquiposPorAcademiaService.listarEquiposPorAcademia(academiaId));
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @GetMapping("/{id}/planilla")
    public ResponseEntity<byte[]> exportarPlanilla(@PathVariable UUID id) throws IOException {
        byte[] excelBytes = generarPlanillaService.generarPlanillaExcel(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"planilla_equipo.xlsx\"")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(excelBytes);
    }
}