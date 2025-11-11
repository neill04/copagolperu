package com.neill.copagolperu.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JugadorYaRegistradoException.class)
    public ResponseEntity<Map<String, String>> handleJugadorYaRegistrado(JugadorYaRegistradoException exception) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EdadNoPermitidaException.class)
    public ResponseEntity<Map<String, Object>> handleEdadNoPermitida(EdadNoPermitidaException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", "Edad no permitida para la categoría");
        errorResponse.put("mensaje", ex.getMessage());
        errorResponse.put("dni", ex.getDni());
        errorResponse.put("fechaNacimiento", ex.getFechaNacimiento());
        errorResponse.put("categoria", ex.getCategoria().name());
        errorResponse.put("edadJugador", ex.getEdadJugador());
        errorResponse.put("aniosPermitidos", ex.getAnosPermitidos());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}