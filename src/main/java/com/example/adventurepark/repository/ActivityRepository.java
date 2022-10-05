package com.example.adventurepark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.adventurepark.entity.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {
    
}
