package com.example.fitness_tracker_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DeveloperDTO {
    @Email
    private String email;
    @NotBlank
    @Size(message = "Password must be atleast 10 char long ",min=10)//dont use @Min its for numeric fields!!
    private String password;

    public DeveloperDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
