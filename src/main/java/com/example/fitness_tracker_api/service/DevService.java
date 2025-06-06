package com.example.fitness_tracker_api.service;

import com.example.fitness_tracker_api.dto.DevProfileDTO;
import com.example.fitness_tracker_api.dto.DeveloperDTO;
import com.example.fitness_tracker_api.exception.DeveloperNotFoundException;
import com.example.fitness_tracker_api.mapper.ModelMapper;
import com.example.fitness_tracker_api.models.Developer;
import com.example.fitness_tracker_api.repository.DeveloperRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DevService {
    private final DeveloperRepository developerRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public DevService(DeveloperRepository developerRepository, ModelMapper modelMapper,PasswordEncoder passwordEncoder) {
        this.developerRepository = developerRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder=passwordEncoder;
    }

    public Developer getDev()
    {
        return null;
    }

    public long saveDeveloper(DeveloperDTO developerDTO)
    {
        developerDTO.setPassword(passwordEncoder.encode(developerDTO.getPassword()));//encrypts password using bcrypt
        Developer dev=developerRepository.save(modelMapper.convertDeveloperDTOToDeveloper(developerDTO));
        return dev.getId();
    }


    public DevProfileDTO getDeveloperProfile(long id)
    {
       Optional<Developer> optionalDev=developerRepository.findById(id);
       return modelMapper.convertDeveloperToDTO(optionalDev.get());


    }

    public String getDeveloperName(long id)
    {
        var dev=developerRepository.findById(id)
                .orElseThrow(()->new DeveloperNotFoundException("Developer not found"));
        return dev.getEmail();




    }
}
