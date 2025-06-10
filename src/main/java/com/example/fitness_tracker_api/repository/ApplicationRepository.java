package com.example.fitness_tracker_api.repository;

import com.example.fitness_tracker_api.models.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application,Long> {

    /*
    get list of all applications registered by a developer with the latest application first
     */
    public List<Application> findApplicationsByDeveloperIdOrderByUploadTimeDesc(long id);

    /*
    not returning optional since their must be a application for each fitness data
     */
    public Optional<Application> findApplicationByApiKey(String apiKey);


}
