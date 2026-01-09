package com.neill.copagolperu.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "jugadores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Jugador {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipo", nullable = false)
    private Equipo equipo;

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_apoderado", nullable = false)
    private Apoderado apoderado;
     */

    @Column(nullable = false, unique = true, length = 8)
    private String dni;

    @Column(nullable = false, length = 50)
    private String apellidos;

    @Column(nullable = false, length = 50)
    private String nombres;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDate fechaActualizacion;

    @Column(nullable = false)
    private Boolean activo;

    @Column(nullable = true)
    private Integer numeroCamiseta;

    @Column(nullable = true, length = 255)
    private String fotoUrl;
}