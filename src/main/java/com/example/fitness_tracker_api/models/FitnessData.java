package com.example.fitness_tracker_api.models;


public class FitnessData {
    private long id;
    private String username;
    private String activity;
    private int calories;

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

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public FitnessData(long id, String username, String activity, int calories) {
        this.id = id;
        this.username = username;
        this.activity = activity;
        this.calories = calories;
    }


}
