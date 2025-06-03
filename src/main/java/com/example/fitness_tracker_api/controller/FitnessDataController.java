package com.example.fitness_tracker_api.controller;

import com.example.fitness_tracker_api.models.FitnessData;
import com.example.fitness_tracker_api.service.FitnessDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FitnessDataController {

    private final FitnessDataService fitnessDataService;

    public FitnessDataController(FitnessDataService fitnessDataService) {
        this.fitnessDataService = fitnessDataService;
    }

    @GetMapping("/data")
    public ResponseEntity<List<FitnessData>> fitnessData()
    {
        return ResponseEntity.status(200).body(fitnessDataService.getFitnessData());

    }
}
