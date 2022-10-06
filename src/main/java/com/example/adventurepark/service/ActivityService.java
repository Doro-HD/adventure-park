package com.example.adventurepark.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.adventurepark.dto.ActivityResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.adventurepark.entity.Activity;
import com.example.adventurepark.repository.ActivityRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ActivityService {

    final private ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<ActivityResponse> getAll() {
        List<Activity> activities = this.activityRepository.findAll();

        return activities.stream().map(
                activity -> new ActivityResponse(activity, false)
        ).toList();
    }

    public ActivityResponse findById(int id, boolean includeAll) {
        Optional<Activity> activityOptional = this.activityRepository.findById(id);

        Activity activity = activityOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "test"));

        return new ActivityResponse(activity, includeAll);
    }

    
    
}
