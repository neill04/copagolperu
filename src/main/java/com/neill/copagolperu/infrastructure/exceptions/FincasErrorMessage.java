package com.neill.copagolperu.infrastructure.exceptions;

public enum FincasErrorMessage {
    USER_NOT_FOUND("User not found");

    private final String message;

    FincasErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}