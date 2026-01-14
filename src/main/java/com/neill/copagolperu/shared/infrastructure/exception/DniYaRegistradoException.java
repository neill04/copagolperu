package com.neill.copagolperu.shared.infrastructure.exception;

public class DniYaRegistradoException extends RuntimeException {
    public DniYaRegistradoException() {
        super("El Dni ya está registrado.");
    }

}
