package com.example.fitness_tracker_api.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/*
Handles the case when a MethodArgumentNotValid exception is thrown...helps avoid duplicating
the exception handling code

 */
@RestControllerAdvice
public class ValidationExeptionHandler {


    /*
    for the below method we can get:-

    {
    "username":"Username cannot be empty",
    "calories":"Calories cant be negative"

    }
     */
@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationExceptions(MethodArgumentNotValidException e)
    {
        Map<String,String> errors=new HashMap<>();
        e.getBindingResult().getFieldErrors()
                .forEach(error->errors.put(error.getField(),error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }



}
