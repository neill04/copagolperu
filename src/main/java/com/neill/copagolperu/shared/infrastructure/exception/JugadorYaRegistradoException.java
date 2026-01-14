package com.neill.copagolperu.shared.infrastructure.exception;

public class JugadorYaRegistradoException extends RuntimeException {
    public JugadorYaRegistradoException(String dni) {
        super("El jugador con DNI: " + dni + " ya está inscrito en otro equipo o en este equipo.");
    }
}