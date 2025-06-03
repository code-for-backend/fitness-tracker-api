package com.example.fitness_tracker_api.service;

import com.example.fitness_tracker_api.models.FitnessData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FitnessDataService {
    private static List<FitnessData> data= List.of(new FitnessData(1L,"first","running",1000),
            new FitnessData(2L,"second","burpees",500));

    public List<FitnessData> getFitnessData()
    {
        return data;
    }
}
