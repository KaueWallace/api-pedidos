package com.api.pedido.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.api.pedido.dtos.ErrorResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleRuntimeException(
            RuntimeException ex) {

        return ResponseEntity
                .badRequest()
                .body(
                    new ErrorResponseDTO(
                        ex.getMessage()
                    )
                );
    }

}