package com.shop.sport.Controller;

import com.shop.sport.Entity.Activity;
import com.shop.sport.Entity.Category;
import com.shop.sport.Response.Response;
import com.shop.sport.Service.ActivityService;
import com.shop.sport.Utils.PushNoti.FirebaseMessageService;
import com.shop.sport.Utils.PushNoti.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/activity-management")
public class ActivityController {

    Response response = Response.getInstance();

    @Autowired
    private ActivityService activityService;


    @Autowired
    private FirebaseMessageService firebaseService;

    @PostMapping("/activities")
    public ResponseEntity<Object> createActivity(@RequestBody Map<String, String> body) {
        try {
            String activityName = body.get("name");
            if (activityService.isExsitActivity(activityName))
                return response.generateResponse("activity is Exsit", HttpStatus.OK, null);
            Activity activity = activityService.createActivity(activityName);

            return response.generateResponse("create activity succesfully", HttpStatus.OK, activity);
        } catch (Exception e) {
            return response.generateResponse("create activity fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }

    @GetMapping("/activities")
    public ResponseEntity<Object> getAllActivity() {

        try {




            List<Activity> activities = activityService.getAllActivity();

            return response.generateResponse("Get All activities Successfully", HttpStatus.OK, activities);
        } catch (Exception e) {
            return response.generateResponse("Get All activities fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }

    @PutMapping("/activities/{id}")
    public ResponseEntity<Object> updateActivity(
            @RequestBody Map<String, String> body,
            @PathVariable long id
    ) {

        try {

            String name = body.get("name");

            Activity activity = activityService.updateActivity(id, name);

            return response.generateResponse("Get All activities Successfully", HttpStatus.OK, activity);
        } catch (Exception e) {
            return response.generateResponse("Get All activities fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }

    @DeleteMapping("/activities/{id}")
    public ResponseEntity<Object> deleteActivity(
            @PathVariable long id
    ) {
        try {
            if (!activityService.deleteActivity(id))
                return response.generateResponse("Delete activities fail", HttpStatus.BAD_REQUEST, false);
            return response.generateResponse("Delete activities Successfully", HttpStatus.OK, true);
        } catch (Exception e) {
            return response.generateResponse("Delete activities fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }
}
