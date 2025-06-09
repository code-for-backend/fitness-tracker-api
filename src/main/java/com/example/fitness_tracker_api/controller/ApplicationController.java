package com.example.fitness_tracker_api.controller;

import com.example.fitness_tracker_api.dto.ApplicationDTO;
import com.example.fitness_tracker_api.dto.ApplicationResponseDTO;
import com.example.fitness_tracker_api.exception.DeveloperNotFoundException;
import com.example.fitness_tracker_api.models.Developer;
import com.example.fitness_tracker_api.repository.DeveloperRepository;
import com.example.fitness_tracker_api.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;
@RestController
public class ApplicationController {
    private final ApplicationService applicationService;


    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;

    }


@PostMapping("/api/application/register")
    public ResponseEntity<ApplicationResponseDTO> registerApplication(@Valid @RequestBody ApplicationDTO applicationDTO, Authentication authentication)
    {
        UserDetails userDetails=(UserDetails)authentication.getPrincipal();
        String developerEmail=userDetails.getUsername();
        String apiKey= UUID.randomUUID().toString();//this will be the api key for the app
        applicationService.save(applicationDTO,apiKey,developerEmail);
        ApplicationResponseDTO response=new ApplicationResponseDTO(applicationDTO.getName(),apiKey);
        return ResponseEntity.status(201).body(response);


    }
}
