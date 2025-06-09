package com.example.fitness_tracker_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ApplicationDTO {
    @NotBlank(message = "Your app should have a name")
    private String name;
    @NotBlank(message = "A small description of your app is required")
    private String description;

    public ApplicationDTO(String name, String description) {
        this.name = name;
        this.description = description;
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
}
