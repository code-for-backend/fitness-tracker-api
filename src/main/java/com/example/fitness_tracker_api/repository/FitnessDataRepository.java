package com.example.fitness_tracker_api.repository;

import com.example.fitness_tracker_api.models.Application;
import com.example.fitness_tracker_api.models.FitnessData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FitnessDataRepository extends JpaRepository<FitnessData,Long> {

    @Query(value = "SELECT * FROM fitness_data ORDER BY upload_time DESC",nativeQuery = true)
public List<FitnessData> findAllOrderByUploadTimeDesc();


    /*
    Get the newest fitness data first
     */
    public List<FitnessData> findFitnessDataByApplicationOrderByUploadTimeDesc(Application application);


}
