package com.neill.copagolperu.presentation.controller;

import com.neill.copagolperu.application.dto.AcademiaDTO;
import com.neill.copagolperu.application.service.admin.BuscarAcademiaService;
import com.neill.copagolperu.application.service.admin.EditarAcademiaService;
import com.neill.copagolperu.application.service.admin.ListarAcademiasService;
import com.neill.copagolperu.application.service.admin.RegistrarAcademiaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/academias")
public class AcademiaController {
    private final RegistrarAcademiaService registrarAcademiaService;
    private final EditarAcademiaService editarAcademiaService;
    private final BuscarAcademiaService buscarAcademiaService;
    private final ListarAcademiasService listarAcademiasService;

    public AcademiaController(
            RegistrarAcademiaService registrarAcademiaService,
            EditarAcademiaService editarAcademiaService,
            BuscarAcademiaService buscarAcademiaService,
            ListarAcademiasService listarAcademiasService) {
        this.registrarAcademiaService = registrarAcademiaService;
        this.editarAcademiaService = editarAcademiaService;
        this.buscarAcademiaService = buscarAcademiaService;
        this.listarAcademiasService = listarAcademiasService;
    }

    @PostMapping("/{distritoId}")
    public ResponseEntity<AcademiaDTO> registrarAcademia(@PathVariable Long distritoId, @RequestBody AcademiaDTO academiadto) {
        AcademiaDTO academiaRegistrada = registrarAcademiaService.registrarAcademia(distritoId, academiadto);
        return ResponseEntity.ok(academiaRegistrada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AcademiaDTO> editarAcademia(@PathVariable Long id, @RequestBody AcademiaDTO academiadto) {
        try {
            AcademiaDTO academiaActualizada = editarAcademiaService.editarAcademia(id, academiadto);
            return ResponseEntity.ok(academiaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcademiaDTO> buscarAcademia(@PathVariable Long id) {
        Optional<AcademiaDTO> academia = buscarAcademiaService.buscarAcademia(id);
        return academia.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AcademiaDTO>> listarAcademias() {
        return ResponseEntity.ok(listarAcademiasService.listarAcademias());
    }
}