package com.example.adventurepark.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.adventurepark.entity.Activity;
import com.example.adventurepark.repository.ActivityRepository;

@Service
public class ActivityService {

    final private ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public ArrayList<Activity> getAll() {
        return null;
    }

    public Activity findById(int id) {
        return null;
    }

    
    
}
