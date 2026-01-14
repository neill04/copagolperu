package com.neill.copagolperu.shared.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String MESSAGE_KEY = "message";

    @ExceptionHandler(DniYaRegistradoException.class)
    public ResponseEntity<Map<String, String>> handleDniYaRegistrado(DniYaRegistradoException exception) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put(MESSAGE_KEY, exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JugadorYaRegistradoException.class)
    public ResponseEntity<Map<String, String>> handleJugadorYaRegistrado(JugadorYaRegistradoException exception) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put(MESSAGE_KEY, exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EdadNoPermitidaException.class)
    public ResponseEntity<Map<String, Object>> handleEdadNoPermitida(EdadNoPermitidaException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", "Edad no permitida para la categoría");
        errorResponse.put(MESSAGE_KEY, ex.getMessage());
        errorResponse.put("dni", ex.getDni());
        errorResponse.put("fechaNacimiento", ex.getFechaNacimiento());
        errorResponse.put("categoria", ex.getCategoria().name());
        errorResponse.put("edadJugador", ex.getEdadJugador());
        errorResponse.put("aniosPermitidos", ex.getAnosPermitidos());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}