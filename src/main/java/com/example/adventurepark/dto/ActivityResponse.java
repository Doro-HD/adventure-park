package com.example.adventurepark.dto;

import com.example.adventurepark.entity.Activity;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActivityResponse {
    
    private int id;

    private String name;

    private int ageRestriction;

    private String description;

    public ActivityResponse(Activity activity, boolean includeAll) {
        this.name = activity.getName();
        this.ageRestriction = activity.getAgeRestriction();
        this.description = activity.getDescription();
        if(includeAll) {
            this.id = activity.getId();
        }
    }

    public static Activity getActivityEntity(ActivityResponse activityResponse) {
        return new Activity(activityResponse.id, activityResponse.name, activityResponse.ageRestriction, activityResponse.description); 
    }

}
