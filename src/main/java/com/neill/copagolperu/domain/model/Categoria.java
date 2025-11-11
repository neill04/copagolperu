package com.neill.copagolperu.domain.model;

import java.time.LocalDate;
import java.time.Period;

public enum Categoria {
    sub5(5),
    sub6(6),
    sub7(7),
    sub8(8),
    sub9(9),
    sub10(10),
    sub11(11),
    sub12(12),
    sub13(13),
    sub14(14),
    sub15(15),
    sub16(16);

    private final int edad;

    Categoria(int edad) {
        this.edad = edad;
    }

    public int getEdad() {
        return edad;
    }

    public int[] getAniosNacimientoPermitidos() {
        int anioActual = LocalDate.now().getYear();
        int anioMenor = anioActual - this.edad;
        int anioMayor = anioMenor + 1;
        return new int[]{anioMenor, anioMayor};
    }

    public boolean esFechaNacimientoValida(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) return false;

        int[] aniosPermitidos = getAniosNacimientoPermitidos();
        int anioNacimiento = fechaNacimiento.getYear();

        return anioNacimiento >= aniosPermitidos[0] && anioNacimiento <= aniosPermitidos[1];
    }

    public static int calcularEdad(LocalDate fechaNacimiento) {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }
}