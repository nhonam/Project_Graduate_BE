package com.shop.sport.Controller;

import com.shop.sport.Entity.Brand;
import com.shop.sport.Entity.Special;
import com.shop.sport.Response.Response;
import com.shop.sport.Service.BrandService;
import com.shop.sport.Service.SpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/special-management")
public class SpecialController {

    Response response = Response.getInstance();

    @Autowired
    private SpecialService specialService;

    

    @PostMapping("/specials")
    public ResponseEntity<Object> create(@RequestBody Map<String, String> body) {
        try {
            String name = body.get("name");
            if (specialService.isExsitByName(name))
                return response.generateResponse("special is Exsit", HttpStatus.OK, null);
            Special special = specialService.create(name);

            return response.generateResponse("special brand succesfully", HttpStatus.OK, special);
        } catch (Exception e) {
            return response.generateResponse("special brand fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }

    @GetMapping("/specials")
    public ResponseEntity<Object> getAll() {

        try {
            List<Special> specials = specialService.getAll();

            return response.generateResponse("Get All special Successfully", HttpStatus.OK, specials);
        } catch (Exception e) {
            return response.generateResponse("Get All special fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }

    @PutMapping("/specials/{id}")
    public ResponseEntity<Object> update(
            @RequestBody Map<String, String> body,
            @PathVariable long id
    ) {

        try {

            String name = body.get("name");

            Special special = specialService.update(id, name);

            return response.generateResponse("Get All special Successfully", HttpStatus.OK, special);
        } catch (Exception e) {
            return response.generateResponse("Get All special fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }

    @DeleteMapping("/specials/{id}")
    public ResponseEntity<Object> delete(
            @PathVariable long id
    ) {
        try {
            if (!specialService.delete(id))
                return response.generateResponse("Delete special fail", HttpStatus.BAD_REQUEST, false);
            return response.generateResponse("Delete special Successfully", HttpStatus.OK, true);
        } catch (Exception e) {
            return response.generateResponse("Delete special fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }
}
