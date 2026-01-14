package com.neill.copagolperu.shared.infrastructure.exception;

public class AcademiaAlreadyAssignedException extends RuntimeException {
    public AcademiaAlreadyAssignedException(String message) {
        super(message);
    }
}