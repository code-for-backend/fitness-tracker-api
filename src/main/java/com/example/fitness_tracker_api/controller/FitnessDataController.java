package com.example.fitness_tracker_api.controller;

import com.example.fitness_tracker_api.dto.FitnessDataDTO;
import com.example.fitness_tracker_api.dto.FitnessDataResponseDTO;
import com.example.fitness_tracker_api.models.FitnessData;
import com.example.fitness_tracker_api.service.FitnessDataService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FitnessDataController {

    private final FitnessDataService fitnessDataService;

    public FitnessDataController(FitnessDataService fitnessDataService) {
        this.fitnessDataService = fitnessDataService;
    }

    @GetMapping("/api/tracker")
    public ResponseEntity<List<FitnessDataResponseDTO>> fitnessData()
    {
       List<FitnessDataResponseDTO> fitnessDataResponseDTO=fitnessDataService.getFitnessData();
      return ResponseEntity.status(200).body(fitnessDataResponseDTO);

    }


    @PostMapping("/api/tracker")
    public ResponseEntity<?> uploadFitnessData(@Valid @RequestBody FitnessDataDTO fitnessDataDTO)
    {
        fitnessDataService.saveFitnessData(fitnessDataDTO);
        return ResponseEntity.status(201).body("Okay");

    }
}
