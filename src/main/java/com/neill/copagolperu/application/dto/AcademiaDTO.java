package com.neill.copagolperu.application.dto;

import java.time.LocalDateTime;

public class AcademiaDTO {
    private Long id;
    private String nombreAcademia;
    private String nombreRepresentante;
    private String dniRepresentante;
    private String logoUrl;
    private String estado;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaActualizacion;

    private Long distritoId;
    private String nombreDistrito;

    protected AcademiaDTO() {}

    public AcademiaDTO(Long id, String nombreAcademia, String nombreRepresentante,
                       String dniRepresentante, String logoUrl, String estado,
                       LocalDateTime fechaRegistro, LocalDateTime fechaActualizacion,
                       Long distritoId, String nombreDistrito) {
        this.id = id;
        this.nombreAcademia = nombreAcademia;
        this.nombreRepresentante = nombreRepresentante;
        this.dniRepresentante = dniRepresentante;
        this.logoUrl = logoUrl;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.fechaActualizacion = fechaActualizacion;
        this.distritoId = distritoId;
        this.nombreDistrito = nombreDistrito;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreAcademia() {
        return nombreAcademia;
    }

    public void setNombreAcademia(String nombreAcademia) {
        this.nombreAcademia = nombreAcademia;
    }

    public String getNombreRepresentante() {
        return nombreRepresentante;
    }

    public void setNombreRepresentante(String nombreRepresentante) {
        this.nombreRepresentante = nombreRepresentante;
    }

    public String getDniRepresentante() {
        return dniRepresentante;
    }

    public void setDniRepresentante(String dniRepresentante) {
        this.dniRepresentante = dniRepresentante;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Long getDistritoId() {
        return distritoId;
    }

    public void setDistritoId(Long distritoId) {
        this.distritoId = distritoId;
    }

    public String getNombreDistrito() {
        return nombreDistrito;
    }

    public void setNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }
}