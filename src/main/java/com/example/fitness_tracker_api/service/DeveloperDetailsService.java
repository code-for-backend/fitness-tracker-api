package com.example.fitness_tracker_api.service;

import com.example.fitness_tracker_api.adapter.DeveloperAdapter;
import com.example.fitness_tracker_api.models.Developer;
import com.example.fitness_tracker_api.repository.DeveloperRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DeveloperDetailsService implements UserDetailsService {
    private final DeveloperRepository developerRepository;

    public DeveloperDetailsService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

//we are using email instead of username as the unique identity for a developer
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var dev=developerRepository.findDeveloperByEmail(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
        return new DeveloperAdapter(dev);



    }
}
