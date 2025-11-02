package com.neill.copagolperu.presentation.controller;

import com.neill.copagolperu.application.dto.request.DelegadoRequest;
import com.neill.copagolperu.application.dto.response.DelegadoResponse;
import com.neill.copagolperu.application.service.academia.delegado.BuscarDelegadoService;
import com.neill.copagolperu.application.service.academia.delegado.EditarDelegadoService;
import com.neill.copagolperu.application.service.academia.delegado.ListarDelegadosPorAcademiaService;
import com.neill.copagolperu.application.service.academia.delegado.RegistrarDelegadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/academias/{academiaId}/delegados")
public class DelegadoController {
    private final RegistrarDelegadoService registrarDelegadoService;
    private final EditarDelegadoService editarDelegadoService;
    private final BuscarDelegadoService buscarDelegadoService;
    private final ListarDelegadosPorAcademiaService listarDelegadosPorAcademiaService;

    public DelegadoController(RegistrarDelegadoService registrarDelegadoService,
                              EditarDelegadoService editarDelegadoService,
                              BuscarDelegadoService buscarDelegadoService,
                              ListarDelegadosPorAcademiaService listarDelegadosPorAcademiaService) {
        this.registrarDelegadoService = registrarDelegadoService;
        this.editarDelegadoService = editarDelegadoService;
        this.buscarDelegadoService = buscarDelegadoService;
        this.listarDelegadosPorAcademiaService = listarDelegadosPorAcademiaService;
    }

    @PostMapping
    public ResponseEntity<DelegadoResponse> registrarDelegado(@PathVariable UUID academiaId, @RequestBody DelegadoRequest request) {
        DelegadoResponse newDelegado = registrarDelegadoService.registrarDelegado(academiaId, request);
        return ResponseEntity.ok(newDelegado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DelegadoResponse> editarDelegado(@PathVariable UUID id, @RequestBody DelegadoRequest request) {
        try {
            DelegadoResponse updatedDelegado = editarDelegadoService.editarDelegado(id, request);
            return ResponseEntity.ok(updatedDelegado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DelegadoResponse> buscarDelegado(@PathVariable UUID id) {
        Optional<DelegadoResponse> delegado = buscarDelegadoService.buscarDelegado(id);
        return delegado.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<DelegadoResponse>> listarDelegadosPorAcademia(@PathVariable UUID academiaId) {
        return ResponseEntity.ok(listarDelegadosPorAcademiaService.listarDelegadosPorAcademia(academiaId));
    }
}