package com.example.fitness_tracker_api.models;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor //hibernate requires a no-arg constructor
@Entity
public class FitnessData {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String username;
    private String activity;
    private int calories;
    private int duration;
    private LocalDateTime uploadTime=LocalDateTime.now();

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public FitnessData(String username, String activity, int duration,int calories) {

        this.username = username;
        this.activity = activity;
        this.calories = calories;
        this.duration=duration;
    }


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

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }




}
