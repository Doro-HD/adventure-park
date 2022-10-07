package com.example.adventurepark.dto;

import com.example.adventurepark.entity.Activity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRequest {

    private int id;

    private String name;

    private int ageRestriction;

    private String description;

    public static Activity getActivityEntity(ActivityRequest activityRequest) {
        return new Activity(activityRequest.id, activityRequest.name, activityRequest.ageRestriction, activityRequest.description);
    }
    
}
