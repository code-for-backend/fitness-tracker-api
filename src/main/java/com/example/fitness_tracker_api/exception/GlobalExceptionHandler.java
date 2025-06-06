package com.example.fitness_tracker_api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationExceptions(MethodArgumentNotValidException e)
    {
        Map<String,String> errors=new HashMap<>();
        e.getBindingResult().getFieldErrors()
                .forEach(error->errors.put(error.getField(),error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(DeveloperNotFoundException.class)
    public ResponseEntity<String> handleDeveloperNotFoundException(DeveloperNotFoundException d)
    {
        return ResponseEntity.status(404).body(d.getMessage());
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAcessDeniedException(AccessDeniedException e)
    {
        return ResponseEntity.status(403).body(e.getMessage());
    }

    public ResponseEntity<String> handleGenericException(Exception e)
    {
        return ResponseEntity.status(500).body("Something went wwrong!");
    }

}
