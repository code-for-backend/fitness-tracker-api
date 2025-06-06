package com.example.fitness_tracker_api.mapper;

import com.example.fitness_tracker_api.dto.DevProfileDTO;
import com.example.fitness_tracker_api.dto.DeveloperDTO;
import com.example.fitness_tracker_api.dto.FitnessDataDTO;
import com.example.fitness_tracker_api.dto.FitnessDataResponseDTO;
import com.example.fitness_tracker_api.models.Developer;
import com.example.fitness_tracker_api.models.FitnessData;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {

    public FitnessDataResponseDTO convertFitnessDataToDTO(FitnessData fitnessData)
    {
        return new FitnessDataResponseDTO(fitnessData.getId(),fitnessData.getUsername(), fitnessData.getActivity(),
                fitnessData.getDuration(),fitnessData.getCalories());

    }

    public FitnessData convertDTOToFitnessData(FitnessDataDTO fitnessDataDTO)
    {
        return new FitnessData(fitnessDataDTO.getUsername(), fitnessDataDTO.getActivity(),fitnessDataDTO.getDuration()
                , fitnessDataDTO.getCalories());
    }


    public Developer convertDeveloperDTOToDeveloper(DeveloperDTO developerDTO)
    {
        return new Developer(developerDTO.getEmail(),developerDTO.getPassword());
    }


    public DevProfileDTO convertDeveloperToDTO(Developer developer)
    {
        return new DevProfileDTO(developer.getId(),developer.getEmail());
    }



}
