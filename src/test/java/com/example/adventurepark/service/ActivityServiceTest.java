package com.example.adventurepark.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.example.adventurepark.dto.ActivityResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.adventurepark.entity.Activity;
import com.example.adventurepark.repository.ActivityRepository;

@DataJpaTest
public class ActivityServiceTest {
    
    public ActivityService activityService;

    public static ActivityRepository activityRepositoryStatic;

    private static int expectedActivityCount;

    private static int id1;

    private static int id2;

    private static int id3;


    @BeforeAll
    public static void setupData(@Autowired ActivityRepository activityRepository) {
        activityRepositoryStatic = activityRepository;
        activityRepository.deleteAll();

        List<Activity> activities = List.of(
            new Activity("paintball", 18, "shooting game"),
            new Activity("klatrevæg", 15, "climb to the top!"),
            new Activity("GoKart", 21, "speed is key!")
        );

        activityRepository.saveAll(activities);

        id1 = activities.get(0).getId();
        id2 = activities.get(1).getId();
        id3 = activities.get(2).getId();

        expectedActivityCount = activities.size();

    }

    @BeforeEach
    public void setActivityService() {
        activityService = new ActivityService(activityRepositoryStatic);
    }

    
    @Test
    void getAll() {
        int activityCount = this.activityService.getAll().size();

        assertEquals(expectedActivityCount, activityCount);
    }

    @Test
    void findById() {
        ActivityResponse activity1 = this.activityService.findById(id1, true);
        ActivityResponse activity2 = this.activityService.findById(id2, true);
        ActivityResponse activity3 = this.activityService.findById(id3, true);

        assertEquals(id1, activity1.getId());
        assertEquals(id2, activity2.getId());
        assertEquals(id3, activity3.getId());
    }
}
