package com.neill.copagolperu.shared.infrastructure.exception;

import com.neill.copagolperu.inscripciones.domain.model.Categoria;
import java.time.LocalDate;

public class EdadNoPermitidaException extends RuntimeException {
    private final String dni;
    private final LocalDate fechaNacimiento;
    private final Categoria categoria;
    private final int edadJugador;
    private final int[] anosPermitidos;

    public EdadNoPermitidaException(String dni, LocalDate fechaNacimiento,
                                    Categoria categoria, int edadJugador) {
        super(String.format(
                "El jugador con DNI %s (nacido en %d) tiene %d años y no puede jugar en la categoría %s. " +
                        "Años de nacimiento permitidos: %d-%d",
                dni,
                fechaNacimiento.getYear(),
                edadJugador,
                categoria.name(),
                categoria.getAniosNacimientoPermitidos()[0],
                categoria.getAniosNacimientoPermitidos()[1]
        ));
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.categoria = categoria;
        this.edadJugador = edadJugador;
        this.anosPermitidos = categoria.getAniosNacimientoPermitidos();
    }

    // Getters
    public String getDni() { return dni; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public Categoria getCategoria() { return categoria; }
    public int getEdadJugador() { return edadJugador; }
    public int[] getAnosPermitidos() { return anosPermitidos; }
}