package com.neill.copagolperu.infrastructure.exceptions;

public class FincasException extends RuntimeException {
    private final FincasErrorMessage errorMessage;

    public FincasException(final FincasErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }

    public FincasErrorMessage getErrorMessage() {
        return errorMessage;
    }
}