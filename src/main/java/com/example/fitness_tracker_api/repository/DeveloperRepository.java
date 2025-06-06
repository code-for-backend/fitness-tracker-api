package com.example.fitness_tracker_api.repository;

import com.example.fitness_tracker_api.models.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer,Long> {


    public Optional<Developer> findDeveloperByEmail(String email);

    boolean existsByEmail(String email);
}
