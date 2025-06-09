package com.example.fitness_tracker_api.service;

import com.example.fitness_tracker_api.dto.DevProfileDTO;
import com.example.fitness_tracker_api.dto.DeveloperDTO;
import com.example.fitness_tracker_api.exception.DeveloperNotFoundException;
import com.example.fitness_tracker_api.exception.DeveloperNotUniqueException;
import com.example.fitness_tracker_api.mapper.ModelMapper;
import com.example.fitness_tracker_api.models.Application;
import com.example.fitness_tracker_api.models.Developer;
import com.example.fitness_tracker_api.repository.ApplicationRepository;
import com.example.fitness_tracker_api.repository.DeveloperRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DevService {
    private final DeveloperRepository developerRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationRepository applicationRepository;

    public DevService(DeveloperRepository developerRepository,ApplicationRepository applicationRepository, ModelMapper modelMapper,PasswordEncoder passwordEncoder) {
        this.developerRepository = developerRepository;
        this.applicationRepository=applicationRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder=passwordEncoder;
    }



    public long saveDeveloper(DeveloperDTO developerDTO)
    {
        developerDTO.setPassword(passwordEncoder.encode(developerDTO.getPassword()));//encrypts password using bcrypt
        Developer dev=developerRepository.save(modelMapper.convertDeveloperDTOToDeveloper(developerDTO));
        return dev.getId();
    }


    public DevProfileDTO getDeveloperProfile(Developer developer)
    {
      // Developer developer=developerRepository.findById(id).orElseThrow(()->new DeveloperNotFoundException("Developer not found!"));
        long devId=developer.getId();
        String devEmail=developer.getEmail();
       List<Application> applications=applicationRepository.findApplicationsByDeveloperIdOrderByUploadTimeDesc(devId);

       return new DevProfileDTO(devId,devEmail,applications);



    }

    public Developer findDeveloper(long id)
    {
        return developerRepository.findById(id)
                .orElseThrow(()->new DeveloperNotFoundException("Developer not found"));



    }

    /*
    Check if developer with the email exists
    Needed so that two devs don't have same email

     */

    public boolean developerWithEmailExists(String email)
    {
        return developerRepository.existsByEmail(email);
    }
}
