package com.example.adventurepark.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

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

    public static ActivityRepository activityRepository;

    private static int actualActivityCount;

    private static int id1;

    private static int id2;

    private static int id3;


    @BeforeAll
    public static void setupData(@Autowired ActivityRepository activityRepository) {
        activityRepository = activityRepository;
        activityRepository.deleteAll();

        List<Activity> activities = List.of(
            new Activity("paintball", 18, "shooting game"),
            new Activity("klatrevæg", 15, "climb to the top!"),
            new Activity("GoKart", 21, "speed is key!")
        );

        id1 = activities.get(0).getId();
        id2 = activities.get(1).getId();
        id3 = activities.get(2).getId();

        actualActivityCount = activities.size();

        activityRepository.saveAll(activities);
    }

    @BeforeEach
    public void setActivityService() {
        activityService = new ActivityService(activityRepository);
    }

    
    @Test
    void getAll() {
        int activityCount = this.activityService.getAll().size();

        assertEquals(activityCount , actualActivityCount);
    }

    @Test
    void findById() {
        Activity activity1 = this.activityService.findById(id1);
        Activity activity2 = this.activityService.findById(id2);
        Activity activity3 = this.activityService.findById(id3);

        assertEquals(activity1.getId(), id1);
        assertEquals(activity2.getId(), id2);
        assertEquals(activity3.getId(), id3);
    }
}
