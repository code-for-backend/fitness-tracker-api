package com.example.fitness_tracker_api.dto;


import com.example.fitness_tracker_api.models.Application;
import lombok.NoArgsConstructor;

    /*
    Look for @JsonIgnore in Application entity
    //When we send the list of applications for /api/developers/id endpoint then jackson will ignore the Developer
    field.E.g
    {
    "id": 53,
    "name": "yet another fitness app",
    "description": "my shiny new fitness app",
    "apiKey": "b8f88a6f-52de-4ea8-bb4e-b334dc0931c8",
    "uploadTime": "2025-06-10T11:41:49.764974"
    // No 'developer' field at all
}

Otherwise, jackson has to deal with circular reference issues


{
    "id": 8,                    // ← Developer info is already here
    "email": "backenddev@test.com",  // ← Developer info is already here
    "applications": [
        {
            "id": 53,
            "name": "app name"
            // No need to repeat developer info here
        }
    ]
}

In this project,I made improvements for improviing api response time from ~7 seconds to <1 sec.

     */


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
