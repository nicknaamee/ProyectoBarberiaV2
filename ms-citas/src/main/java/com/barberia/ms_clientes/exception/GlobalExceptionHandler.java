package com.barberia.ms_clientes.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarErroresDeValidacion(
        MethodArgumentNotValidException excepcionDeValidacion) {

        Map<String, String> mensajesDeError = new HashMap<>();
        excepcionDeValidacion.getBindingResult().getFieldErrors()
            .forEach(errorEnCampo ->
                mensajesDeError.put(
                    errorEnCampo.getField(),
                    errorEnCampo.getDefaultMessage()
                )
            );

        log.warn("errores de validacion: {}", mensajesDeError);
        return ResponseEntity.badRequest().body(mensajesDeError);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> manejarErroresGenerales(
            RuntimeException excepcionGeneral) {

        log.error("error general: {}", excepcionGeneral.getMessage());
        Map<String, String> respuestaDeError = new HashMap<>();
        respuestaDeError.put("mensaje de error", excepcionGeneral.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuestaDeError);
    }

}

