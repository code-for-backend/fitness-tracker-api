package com.example.fitness_tracker_api.models;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor //hibernate requires
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String description;

    private String apiKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "developer_id",nullable = false) //each application must have a developer
    private Developer developer;

    private LocalDateTime uploadTime=LocalDateTime.now();

    public Application(String name, String description,String apiKey) {
        this.name = name;
        this.description = description;
        this.apiKey = apiKey;
    }

    //This constructor is created specially for constructing instance from dto
    public Application(String name, String description) {
        this.name= name;
        this.description=description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApiKey() {
        return apiKey;
    }


    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
