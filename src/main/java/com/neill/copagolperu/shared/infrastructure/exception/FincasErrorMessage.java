package com.neill.copagolperu.shared.infrastructure.exception;

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