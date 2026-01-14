package com.neill.copagolperu.shared.infrastructure.exception;

public class AcademiaNotFoundException extends RuntimeException {
    public AcademiaNotFoundException(String message) {
        super(message);
    }
}