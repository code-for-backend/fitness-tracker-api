package com.example.fitness_tracker_api.exception;

public class DeveloperNotFoundException extends RuntimeException{

    public DeveloperNotFoundException(String message)
    {
        super(message);
    }

}
