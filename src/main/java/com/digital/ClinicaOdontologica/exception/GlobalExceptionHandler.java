package com.digital.ClinicaOdontologica.exception;

import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends Exception {
    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> procesarNotFoundException(ResourceNotFoundException exception) {
        Map<String, String> exceptionMessage = new HashMap<>();
        exceptionMessage.put("message", "El recurso no fue encontrado: " + exception.getMessage());
        return exceptionMessage;
    }

    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> procesarBadRequest(BadRequestException exception) {
        Map<String, String> exceptionMessage = new HashMap<>();
        exceptionMessage.put("message", "El recurso no fue encontrado: " + exception.getMessage());
        return exceptionMessage;
    }


    @ExceptionHandler({JsonParseException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> procesarJsonParseException(JsonParseException exception) {
        Map<String, String> exceptionMessage = new HashMap<>();
        exceptionMessage.put("path", String.valueOf(exception.getClass()));
        exceptionMessage.put("message", exception.getMessage());
        return exceptionMessage;
    }


}