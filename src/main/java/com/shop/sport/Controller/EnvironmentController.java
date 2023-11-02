package com.shop.sport.Controller;

import com.shop.sport.Entity.Activity;
import com.shop.sport.Entity.Environment;
import com.shop.sport.Response.Response;
import com.shop.sport.Service.ActivityService;
import com.shop.sport.Service.EnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/environment-management")
public class EnvironmentController {

    Response response = Response.getInstance();

    @Autowired
    private EnvironmentService environmentService;

    @PostMapping("/environments")
    public ResponseEntity<Object> createEnvironment(@RequestBody Map<String, String> body) {
        try {
            String Name = body.get("name");
            if (environmentService.isExsitByName(Name))
                return response.generateResponse("environment is Exsit", HttpStatus.OK, null);
            Environment environment = environmentService.create(Name);

            return response.generateResponse("create environment succesfully", HttpStatus.OK, environment);
        } catch (Exception e) {
            return response.generateResponse("create environment fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }

    @GetMapping("/environments")
    public ResponseEntity<Object> getAllEnvironments() {

        try {
            List<Environment> environments = environmentService.getAll();

            return response.generateResponse("Get All environment Successfully", HttpStatus.OK, environments);
        } catch (Exception e) {
            return response.generateResponse("Get All environment fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }

    @PutMapping("/environments/{id}")
    public ResponseEntity<Object> updateEnvironment(
            @RequestBody Map<String, String> body,
            @PathVariable long id
    ) {

        try {

            String name = body.get("name");

            Environment environment = environmentService.update(id, name);

            return response.generateResponse("Get All Environment Successfully", HttpStatus.OK, environment);
        } catch (Exception e) {
            return response.generateResponse("Get All Environment fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }

    @DeleteMapping("/environments/{id}")
    public ResponseEntity<Object> deleteEnvironment(
            @PathVariable long id
    ) {
        try {
            if (!environmentService.delete(id))
                return response.generateResponse("Delete environment fail", HttpStatus.BAD_REQUEST, false);
            return response.generateResponse("Delete environment Successfully", HttpStatus.OK, true);
        } catch (Exception e) {
            return response.generateResponse("Delete environment fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }
}
