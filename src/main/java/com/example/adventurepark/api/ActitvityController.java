package com.example.adventurepark.api;

import com.example.adventurepark.dto.ActivityRequest;
import com.example.adventurepark.dto.ActivityResponse;
import org.springframework.web.bind.annotation.*;
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
    public List<ActivityResponse> getAll(){
        return this.activityService.getAll();
    }

    @GetMapping("/{id}")
    public ActivityResponse findById(@PathVariable("id") int id) {
        return this.activityService.findById(id, false);
    }

    @PostMapping
    public ActivityResponse create(@RequestBody ActivityRequest activityRequest, @CookieValue("jwt") String jwtAccessToken) {

        return this.activityService.create(activityRequest, jwtAccessToken);
    }

    @PutMapping("/{id}")
    public ActivityResponse editById(@PathVariable("id") int id, @RequestBody ActivityRequest activityRequest, @CookieValue("jwt") String jwtAccessToken) {
        return this.activityService.editById(id, activityRequest, true, jwtAccessToken);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") int id, @CookieValue("jwt") String jwtAccessToken) {
        this.activityService.deleteById(id, jwtAccessToken);
    }
}
