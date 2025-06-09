package com.example.fitness_tracker_api.service;

import com.example.fitness_tracker_api.dto.ApplicationDTO;
import com.example.fitness_tracker_api.exception.DeveloperNotFoundException;
import com.example.fitness_tracker_api.mapper.ModelMapper;
import com.example.fitness_tracker_api.models.Application;
import com.example.fitness_tracker_api.models.Developer;
import com.example.fitness_tracker_api.repository.ApplicationRepository;
import com.example.fitness_tracker_api.repository.DeveloperRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ModelMapper modelMapper;
    private final DeveloperRepository developerRepository;

    public ApplicationService(ApplicationRepository applicationRepository,ModelMapper modelMapper,DeveloperRepository developerRepository) {
        this.applicationRepository = applicationRepository;
        this.modelMapper=modelMapper;
        this.developerRepository=developerRepository;
    }

    public void save(ApplicationDTO applicationDTO,String apiKey,String developerEmail)
    {
        Application application=modelMapper.convertDTOToApplication(applicationDTO);
        //the below method will always return a developer because the endpoint is accesible only for authenticated dev
        Developer developer=developerRepository.findDeveloperByEmail(developerEmail).orElseThrow(()->new DeveloperNotFoundException("Dev not found"));
        application.setDeveloper(developer);
        application.setApiKey(apiKey);
        applicationRepository.save(application);



    }


}
