package com.neill.copagolperu.domain.model.ubicacion;

import jakarta.persistence.*;

@Entity
@Table(name = "provincias")
public class Provincia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_provincia")
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre_provincia;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_departamento", nullable = false)
    private Departamento departamento;

    public String getNombre_provincia() {
        return nombre_provincia;
    }

    public void setNombre_provincia(String nombre_provincia) {
        this.nombre_provincia = nombre_provincia;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}