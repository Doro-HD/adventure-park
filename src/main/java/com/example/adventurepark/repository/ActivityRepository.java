package com.example.adventurepark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.adventurepark.entity.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {
    
}
