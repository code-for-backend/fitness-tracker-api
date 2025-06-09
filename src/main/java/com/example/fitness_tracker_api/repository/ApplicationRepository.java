package com.example.fitness_tracker_api.repository;

import com.example.fitness_tracker_api.models.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application,Long> {




}
