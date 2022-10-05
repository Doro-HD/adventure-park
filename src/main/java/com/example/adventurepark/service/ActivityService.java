package com.example.adventurepark.service;

import org.springframework.stereotype.Service;

import com.example.adventurepark.repository.ActivityRepository;

@Service
public class ActivityService {

    final private ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }
    
}
