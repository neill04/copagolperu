package com.neill.copagolperu.application.exception;

public class AcademiaAlreadyAssignedException extends RuntimeException {
    public AcademiaAlreadyAssignedException(String message) {
        super(message);
    }
}