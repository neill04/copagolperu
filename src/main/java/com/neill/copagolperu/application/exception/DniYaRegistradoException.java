package com.neill.copagolperu.application.exception;

public class DniYaRegistradoException extends RuntimeException {
    public DniYaRegistradoException() {
        super("El Dni ya está registrado.");
    }

}
