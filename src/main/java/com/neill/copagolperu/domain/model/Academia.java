package com.neill.copagolperu.domain.model;

import com.neill.copagolperu.domain.model.ubicacion.Distrito;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "academias")
public class Academia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombreAcademia;

    @Column(nullable = false, length = 100)
    private String nombreRepresentante;

    @Column(nullable = false, length = 8)
    private String dniRepresentante;

    @Column(nullable = true, length = 100)
    private String logoUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoAcademia estado = EstadoAcademia.ACTIVO;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    @Column(nullable = false)
    private LocalDateTime fechaActualizacion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_distrito", nullable = false)
    private Distrito distrito;

    public enum EstadoAcademia {
        ACTIVO,
        DESACTIVO
    }

    // Constructor para jpa
    public Academia() {}

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

    public EstadoAcademia getEstado() {
        return estado;
    }

    public void setEstado(EstadoAcademia estado) {
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

    public Distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }
}