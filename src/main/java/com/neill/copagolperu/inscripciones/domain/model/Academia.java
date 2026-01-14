package com.neill.copagolperu.inscripciones.domain.model;

import com.neill.copagolperu.shared.domain.model.ubicacion.Distrito;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "academias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Academia {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String nombreAcademia;

    @Column(nullable = false, length = 100)
    private String nombreRepresentante;

    @Column(nullable = false, unique = true, length = 8)
    private String dniRepresentante;

    @Column(nullable = false, length = 9)
    private String telefonoRepresentante;

    @Column(nullable = false)
    private String liga;

    @Column(nullable = true, length = 100)
    private String logoUrl;

    @Column(nullable = false)
    private Boolean activo;

    @Column(nullable = false, updatable = false)
    private LocalDate fechaRegistro;

    @Column(nullable = false)
    private LocalDate fechaActualizacion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_distrito", nullable = false)
    private Distrito distrito;

    @OneToMany(mappedBy = "academia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Equipo> equipos = new ArrayList<>();
}