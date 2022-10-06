package com.example.adventurepark.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.adventurepark.entity.Activity;
import com.example.adventurepark.service.ActivityService;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/api/activity")
public class ActitvityController {

    final private ActivityService activityService;

    public ActitvityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    public ArrayList<Activity> getAll(){
        return null;
    }

    @GetMapping("/{id}")
    public Activity findById(@PathVariable("id") int id) {
        return null;
    }
}
