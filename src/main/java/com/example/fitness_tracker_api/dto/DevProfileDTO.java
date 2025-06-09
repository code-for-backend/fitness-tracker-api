package com.example.fitness_tracker_api.dto;


import com.example.fitness_tracker_api.models.Application;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
public class DevProfileDTO {
    private long id;
    private String email;
    private List<Application> applications;

    public DevProfileDTO(long id, String email,List<Application> applications) {
        this.id = id;
        this.email = email;
        this.applications=applications;


    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
