package com.shop.sport.Controller;

import com.shop.sport.Entity.Activity;
import com.shop.sport.Entity.Unit;
import com.shop.sport.Response.Response;
import com.shop.sport.Service.ActivityService;
import com.shop.sport.Service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/unit-management")
public class UnitController {

    Response response = Response.getInstance();

    @Autowired
    private UnitService unitService;

    @PostMapping("/units")
    public ResponseEntity<Object> createActivity(@RequestBody Map<String, String> body) {
        try {
            String activityName = body.get("name");
            if (unitService.isExsitActivity(activityName))
                return response.generateResponse("units is Exsit", HttpStatus.OK, null);
            Unit activity = unitService.createUnit(activityName);

            return response.generateResponse("create units succesfully", HttpStatus.OK, activity);
        } catch (Exception e) {
            return response.generateResponse("create units fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }

    @GetMapping("/units")
    public ResponseEntity<Object> getAllActivity() {

        try {
            List<Unit> activities = unitService.getAllUnit();

            return response.generateResponse("Get All units Successfully", HttpStatus.OK, activities);
        } catch (Exception e) {
            return response.generateResponse("Get All units fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }

    @PutMapping("/units/{id}")
    public ResponseEntity<Object> updateActivity(
            @RequestBody Map<String, String> body,
            @PathVariable long id
    ) {

        try {

            String name = body.get("name");

            Unit activity = unitService.updateUnit(id, name);

            return response.generateResponse("Get All units Successfully", HttpStatus.OK, activity);
        } catch (Exception e) {
            return response.generateResponse("Get All units fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }

    @DeleteMapping("/units/{id}")
    public ResponseEntity<Object> deleteActivity(
            @PathVariable long id
    ) {
        try {

            if(!unitService.check_delete_unit(id)) {
                return response.generateResponse("không thể xóa", HttpStatus.BAD_REQUEST, false);

            }

            if (!unitService.deleteUnit(id))
                return response.generateResponse("Delete units fail", HttpStatus.BAD_REQUEST, false);
            return response.generateResponse("Delete units Successfully", HttpStatus.OK, true);
        } catch (Exception e) {
            return response.generateResponse("Delete units fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }
}
