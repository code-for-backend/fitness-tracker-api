package com.example.fitness_tracker_api.exception;

public class DeveloperNotUniqueException extends RuntimeException{
    public DeveloperNotUniqueException(String message)
    {
        super(message);
    }
}
