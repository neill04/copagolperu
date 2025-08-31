package com.neill.copagolperu.presentation.controller;

import com.neill.copagolperu.application.dto.UbicacionDTO;
import com.neill.copagolperu.application.service.admin.ubicacion.ListarDepartamentosService;
import com.neill.copagolperu.application.service.admin.ubicacion.ListarDistritosService;
import com.neill.copagolperu.application.service.admin.ubicacion.ListarProvinciasService;
import com.neill.copagolperu.domain.model.ubicacion.Departamento;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ubicacion")
public class UbicacionController {
    private final ListarDepartamentosService listarDepartamentosService;
    private final ListarProvinciasService listarProvinciasService;
    private final ListarDistritosService listarDistritosService;

    public UbicacionController(
            ListarDistritosService listarDistritosService,
            ListarDepartamentosService listarDepartamentosService,
            ListarProvinciasService listarProvinciasService) {
        this.listarDistritosService = listarDistritosService;
        this.listarDepartamentosService = listarDepartamentosService;
        this.listarProvinciasService = listarProvinciasService;
    }

    @GetMapping("/departamentos")
    public ResponseEntity<List<Departamento>> listarDepartamentos() {
        return ResponseEntity.ok(listarDepartamentosService.listarDepartamentos());
    }

    @GetMapping("/departamentos/{departamentoId}/provincias")
    public ResponseEntity<List<UbicacionDTO.ProvinciaDTO>> listarProvinciasPorDepartamento(@PathVariable Long departamentoId) {
        return ResponseEntity.ok(listarProvinciasService.listarProvinciasPorDepartamentoId(departamentoId));
    }

    @GetMapping("/provincias/{provinciaId}/distritos")
    public ResponseEntity<List<UbicacionDTO.DistritoDTO>> listarDistritosPorProvincia(@PathVariable Long provinciaId) {
        return ResponseEntity.ok(listarDistritosService.listarDistritosPorProvinciaId(provinciaId));
    }
}