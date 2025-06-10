package com.example.fitness_tracker_api.service;

import com.example.fitness_tracker_api.dto.FitnessDataDTO;
import com.example.fitness_tracker_api.dto.FitnessDataResponseDTO;
import com.example.fitness_tracker_api.mapper.ModelMapper;
import com.example.fitness_tracker_api.models.Application;
import com.example.fitness_tracker_api.models.FitnessData;
import com.example.fitness_tracker_api.repository.FitnessDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FitnessDataService {
    private final ModelMapper modelMapper;
    private final FitnessDataRepository fitnessDataRepository;

    public FitnessDataService(ModelMapper modelMapper, FitnessDataRepository fitnessDataRepository) {
        this.modelMapper = modelMapper;
        this.fitnessDataRepository = fitnessDataRepository;
    }


    public List<FitnessDataResponseDTO> getFitnessData(Application application)
    {
        List<FitnessData> fitnessData=fitnessDataRepository.findFitnessDataByApplicationOrderByUploadTimeDesc(application);
       return fitnessData.stream().map(modelMapper::convertFitnessDataToDTO)
               .collect(Collectors.toList());
    }

    public void saveFitnessData(FitnessDataDTO fitnessDataDTO,Application application)
    {
       fitnessDataRepository.save(modelMapper.convertDTOToFitnessData(fitnessDataDTO,application));
    }


}
