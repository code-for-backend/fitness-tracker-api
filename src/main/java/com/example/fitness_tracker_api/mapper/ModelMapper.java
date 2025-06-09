package com.example.fitness_tracker_api.mapper;

import com.example.fitness_tracker_api.dto.*;
import com.example.fitness_tracker_api.models.Application;
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




    public Application convertDTOToApplication(ApplicationDTO applicationDTO)
    {
        return new Application(applicationDTO.getName(), applicationDTO.getDescription());

    }

    public ApplicationResponseDTO convertApplicationToApplicationRegisterSuccessDTO(Application application)
    {
        return new ApplicationResponseDTO(application.getName(),application.getApiKey());


    }



}
