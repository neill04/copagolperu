/*
package com.neill.copagolperu.presentation.controller;

import com.neill.copagolperu.application.dto.request.ApoderadoRequest;
import com.neill.copagolperu.application.dto.response.ApoderadoResponse;
import com.neill.copagolperu.application.service.academia.apoderado.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/apoderados")
public class ApoderadoController {
    private final RegistrarApoderadoService registrarApoderadoService;
    private final EditarApoderadoService editarApoderadoService;
    private final BuscarApoderadoService buscarApoderadoService;
    private final ListarApoderadosPorEquipoService listarApoderadosPorEquipoService;
    private final EliminarApoderadoService eliminarApoderadoService;

    public ApoderadoController(RegistrarApoderadoService registrarApoderadoService,
                               EditarApoderadoService editarApoderadoService,
                               BuscarApoderadoService buscarApoderadoService,
                               ListarApoderadosPorEquipoService listarApoderadosPorEquipoService,
                               EliminarApoderadoService eliminarApoderadoService) {
        this.registrarApoderadoService = registrarApoderadoService;
        this.editarApoderadoService = editarApoderadoService;
        this.buscarApoderadoService = buscarApoderadoService;
        this.listarApoderadosPorEquipoService = listarApoderadosPorEquipoService;
        this.eliminarApoderadoService = eliminarApoderadoService;
    }

    @PostMapping
    public ResponseEntity<ApoderadoResponse> registrarApoderado(@RequestBody ApoderadoRequest request) {
        ApoderadoResponse newApoderado = registrarApoderadoService.registrarApoderado(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newApoderado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApoderadoResponse> editarApoderado(@PathVariable UUID id, @RequestBody ApoderadoRequest request) {
        try {
            ApoderadoResponse updatedApoderado = editarApoderadoService.editarApoderado(id, request);
            return ResponseEntity.ok(updatedApoderado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApoderadoResponse> buscarApoderado(@PathVariable UUID id) {
        Optional<ApoderadoResponse> apoderado = buscarApoderadoService.buscarApoderado(id);
        return apoderado.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarApoderado(@PathVariable UUID id) {
        eliminarApoderadoService.eliminarApoderado(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/equipo/{equipoId}")
    public ResponseEntity<List<ApoderadoResponse>> listarApoderadosPorEquipo(@PathVariable UUID equipoId) {
        return ResponseEntity.ok(listarApoderadosPorEquipoService.listarApoderadosPorEquipo(equipoId));
    }
}
*/