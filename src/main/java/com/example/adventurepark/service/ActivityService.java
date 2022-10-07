package com.example.adventurepark.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.adventurepark.dto.ActivityRequest;
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
                activity -> new ActivityResponse(activity, true)
        ).toList();
    }

    public ActivityResponse findById(int id, boolean includeAll) {
        Optional<Activity> activityOptional = this.activityRepository.findById(id);

        Activity activity = activityOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "test"));

        return new ActivityResponse(activity, includeAll);
    }

    public ActivityResponse create(ActivityRequest activityRequest) {
        Activity activity = new Activity(
                activityRequest.getId(),
                activityRequest.getName(),
                activityRequest.getAgeRestriction(),
                activityRequest.getDescription()
        );

        activity = this.activityRepository.save(activity);

        return new ActivityResponse(activity, true);
    }

    public ActivityResponse editById(int id, ActivityRequest activityRequest, boolean includeAll) {
        Activity activity = ActivityRequest.getActivityEntity(activityRequest);
        activity = this.activityRepository.save(activity);

        ActivityResponse activityResponse = new ActivityResponse(activity, true);

        return activityResponse;
    }

    public void deleteById(int id) {
        this.activityRepository.deleteById(id);
    }

    
    
}
