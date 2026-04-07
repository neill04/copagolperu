package com.neill.copagolperu.inscripciones.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "entrenadores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Entrenador {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_academia", nullable = false)
    private Academia academia;

    @Column(nullable = false, unique = true, length = 8)
    private String dni;

    @Column(nullable = false, length = 50)
    private String apellidos;

    @Column(nullable = false, length = 50)
    private String nombres;

    @Column(length = 50)
    private String licencia;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(length = 9)
    private String telefono;

    @Column(length = 100)
    private String email;

    @Column(length = 255)
    private String fotoUrl;

    @Column(name = "estado_disciplina", length = 50)
    private String estadoDisciplina;

    @Column(length = 255)
    private String observaciones;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDate fechaActualizacion;

    @OneToMany(mappedBy = "entrenador")
    private List<Equipo> equipos;
}