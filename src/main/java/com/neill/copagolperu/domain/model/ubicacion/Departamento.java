package com.neill.copagolperu.domain.model.ubicacion;

import jakarta.persistence.*;

@Entity
@Table(name = "departamentos")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_departamento")
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre_departamento;

    public String getNombre_departamento() {
        return nombre_departamento;
    }

    public void setNombre_departamento(String nombre_departamento) {
        this.nombre_departamento = nombre_departamento;
    }
}