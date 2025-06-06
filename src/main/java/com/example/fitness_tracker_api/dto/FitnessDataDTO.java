package com.example.fitness_tracker_api.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.*;

public class FitnessDataDTO {
    @NotBlank(message = "Username cant be null or empty")
    private String username;
    @NotBlank(message = "Activity cant be null or empty")
    private String activity;
    @Positive(message = "duration cant be negative")
    private int duration;
    @Max(value = 2000)
    private int calories;

    public FitnessDataDTO(String username, String activity, int duration,int calories) {
        this.username = username;
        this.activity = activity;
        this.calories = calories;
        this.duration=duration;

    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
