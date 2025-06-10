package com.example.fitness_tracker_api.dto;


public class FitnessDataResponseDTO {
    private long id;
    private String username,activity;
    private int duration,calories;
    private String application;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public FitnessDataResponseDTO(long id, String username, String activity, int duration, int calories,String application) {
        this.id = id;
        this.username = username;
        this.activity = activity;
        this.duration = duration;
        this.calories = calories;
        this.application =application;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }
}

