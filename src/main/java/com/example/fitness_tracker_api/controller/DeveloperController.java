package com.example.fitness_tracker_api.controller;

import com.example.fitness_tracker_api.dto.ApplicationDTO;
import com.example.fitness_tracker_api.dto.ApplicationResponseDTO;
import com.example.fitness_tracker_api.dto.DevProfileDTO;
import com.example.fitness_tracker_api.dto.DeveloperDTO;
import com.example.fitness_tracker_api.exception.DeveloperNotUniqueException;
import com.example.fitness_tracker_api.service.DevService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeveloperController {
private final DevService devService;
    public DeveloperController(DevService devService) {
        this.devService = devService;
    }

    /*

   Developer emails should be unique
   So adding a check here

     */
    @PostMapping("/api/developers/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody DeveloperDTO developerDTO) {
        if(devService.developerWithEmailExists(developerDTO.getEmail()))
            throw new DeveloperNotUniqueException("Email already exists!!");
        long devId=devService.saveDeveloper(developerDTO);
        String devURL="/api/developers/"+devId;
        return ResponseEntity.status(201).header("location",devURL).body("okay");


    }

    @GetMapping("/api/developers/{id}")
    public ResponseEntity<DevProfileDTO> profile(@PathVariable long id, Authentication authentication)
    {
        UserDetails devDetails=(UserDetails)authentication.getPrincipal();
        String currentDeveloperName=devDetails.getUsername();//get email of authenticated user
        //check if the id belongs to this dev
        String anotherDevName=devService.getDeveloperName(id);
        if(currentDeveloperName.equals(anotherDevName)) {

            DevProfileDTO devProfileDTO = devService.getDeveloperProfile(id);
            return ResponseEntity.status(200).body(devProfileDTO);
        }
        else
            throw new AccessDeniedException("You are not authorized to view this profile!!!");

    }



}
