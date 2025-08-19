package com.neill.copagolperu.domain.model.ubicacion;

import jakarta.persistence.*;

@Entity
@Table(name = "distritos")
public class Distrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_distrito")
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombreDistrito;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_provincia", nullable = false)
    private Provincia provincia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreDistrito() {
        return nombreDistrito;
    }

    public void setNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
}