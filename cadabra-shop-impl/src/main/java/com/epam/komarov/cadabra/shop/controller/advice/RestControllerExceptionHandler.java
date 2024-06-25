package com.epam.komarov.cadabra.shop.controller.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleConflict(Exception ex) {
        return ResponseEntity.internalServerError()
            .body(ex.getMessage());
    }

}
