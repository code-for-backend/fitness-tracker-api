package com.example.fitness_tracker_api.repository;

import com.example.fitness_tracker_api.models.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application,Long> {

    /*
    get list of all applications registered by a developer with the latest application first
     */
    public List<Application> findApplicationsByDeveloperIdOrderByUploadTimeDesc(long id);



}
