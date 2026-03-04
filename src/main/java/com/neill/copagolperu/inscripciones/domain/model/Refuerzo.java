package com.neill.copagolperu.inscripciones.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "refuerzos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Refuerzo {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jugador", unique = true, nullable = false)
    private Jugador jugador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipo_destino", nullable = false)
    private Equipo equipoDestino;

    @Column(nullable = false)
    private LocalDate fechaRegistro;
}
