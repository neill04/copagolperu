package com.neill.copagolperu.inscripciones.api.controller;

import com.neill.copagolperu.inscripciones.application.dto.request.DelegadoRequest;
import com.neill.copagolperu.inscripciones.application.dto.response.DelegadoResponse;
import com.neill.copagolperu.inscripciones.application.service.delegado.BuscarDelegadoService;
import com.neill.copagolperu.inscripciones.application.service.delegado.EditarDelegadoService;
import com.neill.copagolperu.inscripciones.application.service.delegado.ListarDelegadosPorAcademiaService;
import com.neill.copagolperu.inscripciones.application.service.delegado.RegistrarDelegadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("@academiaSecurity.canAccessAcademy(authentication, #academiaId)")
    @PostMapping
    public ResponseEntity<DelegadoResponse> registrarDelegado(@PathVariable UUID academiaId, @RequestBody DelegadoRequest request) {
        DelegadoResponse newDelegado = registrarDelegadoService.registrarDelegado(academiaId, request);
        return ResponseEntity.ok(newDelegado);
    }

    @PreAuthorize("@academiaSecurity.canAccessAcademy(authentication, #academiaId)")
    @PutMapping("/{id}")
    public ResponseEntity<DelegadoResponse> editarDelegado(@PathVariable UUID academiaId, @PathVariable UUID id, @RequestBody DelegadoRequest request) {
        try {
            DelegadoResponse updatedDelegado = editarDelegadoService.editarDelegado(id, request);
            return ResponseEntity.ok(updatedDelegado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("@academiaSecurity.canAccessAcademy(authentication, #academiaId)")
    @GetMapping("/{id}")
    public ResponseEntity<DelegadoResponse> buscarDelegado(@PathVariable UUID academiaId, @PathVariable UUID id) {
        Optional<DelegadoResponse> delegado = buscarDelegadoService.buscarDelegado(id);
        return delegado.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("@academiaSecurity.canAccessAcademy(authentication, #academiaId)")
    @GetMapping
    public ResponseEntity<List<DelegadoResponse>> listarDelegadosPorAcademia(@PathVariable UUID academiaId) {
        return ResponseEntity.ok(listarDelegadosPorAcademiaService.listarDelegadosPorAcademia(academiaId));
    }
}