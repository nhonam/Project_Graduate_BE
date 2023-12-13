package com.shop.sport.Controller;

import com.shop.sport.Entity.Activity;
import com.shop.sport.Entity.Brand;
import com.shop.sport.Response.Response;
import com.shop.sport.Service.ActivityService;
import com.shop.sport.Service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/brand-management")
public class BrandController {

    Response response = Response.getInstance();

    @Autowired
    private BrandService brandService;

    @PostMapping("/brands")
    public ResponseEntity<Object> createBrand(@RequestBody Map<String, String> body) {
        try {
            String name = body.get("name");
            if (brandService.isExsitByName(name))
                return response.generateResponse("brand is Exsit", HttpStatus.OK, null);
            Brand brand = brandService.create(name);

            return response.generateResponse("create brand succesfully", HttpStatus.OK, brand);
        } catch (Exception e) {
            return response.generateResponse("create brand fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }

    @GetMapping("/brands")
    public ResponseEntity<Object> getAllbrands() {

        try {
            List<Brand> brands = brandService.getAll();

            return response.generateResponse("Get All brands Successfully", HttpStatus.OK, brands);
        } catch (Exception e) {
            return response.generateResponse("Get All brands fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }

    @PutMapping("/brands/{id}")
    public ResponseEntity<Object> updatebrands(
            @RequestBody Map<String, String> body,
            @PathVariable long id
    ) {

        try {

            String name = body.get("name");

            Brand brand = brandService.update(id, name);

            return response.generateResponse("Get All brands Successfully", HttpStatus.OK, brand);
        } catch (Exception e) {
            return response.generateResponse("Get All brand fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }

    @DeleteMapping("/brands/{id}")
    public ResponseEntity<Object> deletebrands(
            @PathVariable long id
    ) {
        try {

            //check delete branhd
            if (!brandService.check_delete_brand(id)){
                return response.generateResponse("không thể xóa", HttpStatus.BAD_REQUEST, false);
            }

            if (!brandService.delete(id))
                return response.generateResponse("Delete brands fail", HttpStatus.BAD_REQUEST, false);
            return response.generateResponse("Delete brands Successfully", HttpStatus.OK, true);
        } catch (Exception e) {
            return response.generateResponse("Delete brands fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }
}
