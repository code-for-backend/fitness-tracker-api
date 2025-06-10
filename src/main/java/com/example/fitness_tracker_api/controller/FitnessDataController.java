package com.example.fitness_tracker_api.controller;

import com.example.fitness_tracker_api.dto.FitnessDataDTO;
import com.example.fitness_tracker_api.dto.FitnessDataResponseDTO;
import com.example.fitness_tracker_api.models.Application;
import com.example.fitness_tracker_api.models.FitnessData;
import com.example.fitness_tracker_api.repository.ApplicationRepository;
import com.example.fitness_tracker_api.service.FitnessDataService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FitnessDataController {

    private final FitnessDataService fitnessDataService;
    private final ApplicationRepository applicationRepository;

    public FitnessDataController(FitnessDataService fitnessDataService,ApplicationRepository applicationRepository) {
        this.fitnessDataService = fitnessDataService;
        this.applicationRepository=applicationRepository;
    }

    @GetMapping("/api/tracker")
    public ResponseEntity<?> fitnessData()
    {

      return ResponseEntity.status(200).body("Okay");

    }


    @PostMapping("/api/tracker")
    public ResponseEntity<?> uploadFitnessData(@Valid @RequestBody FitnessDataDTO fitnessDataDTO)
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Application application=(Application)authentication.getPrincipal();
        fitnessDataService.saveFitnessData(fitnessDataDTO,application);
        return ResponseEntity.status(201).body("Okay");

    }
}
