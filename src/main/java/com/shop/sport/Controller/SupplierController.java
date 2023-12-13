package com.shop.sport.Controller;

import com.shop.sport.Entity.Activity;
import com.shop.sport.Entity.Supplier;
import com.shop.sport.Response.Response;
import com.shop.sport.Service.ActivityService;
import com.shop.sport.Service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/supplier-management")
public class SupplierController {

    Response response = Response.getInstance();

    @Autowired
    private SupplierService supplierService;

    @PostMapping("/suppliers")
    public ResponseEntity<Object> createsupplier(@RequestBody Map<String, String> body) {
        try {
            String name = body.get("name");
            if (supplierService.isExsit(name))
                return response.generateResponse("supplier is Exsit", HttpStatus.OK, null);
            Supplier supplier = supplierService.create(name);

            return response.generateResponse("create supplier succesfully", HttpStatus.OK, supplier);
        } catch (Exception e) {
            return response.generateResponse("create supplier fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }

    @GetMapping("/suppliers")
    public ResponseEntity<Object> getSupplier() {

        try {
            List<Supplier> suppliers = supplierService.getAll();

            return response.generateResponse("Get All suppliers Successfully", HttpStatus.OK, suppliers);
        } catch (Exception e) {
            return response.generateResponse("Get All suppliers fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }

    @PutMapping("/suppliers/{id}")
    public ResponseEntity<Object> updateActivity(
            @RequestBody Map<String, String> body,
            @PathVariable long id
    ) {

        try {

            String name = body.get("name");

            Supplier supplier = supplierService.update(id, name);

            return response.generateResponse("Update suppliers Successfully", HttpStatus.OK, supplier);
        } catch (Exception e) {
            return response.generateResponse("Update suppliers fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }

    @DeleteMapping("/suppliers/{id}")
    public ResponseEntity<Object> deleteActivity(
            @PathVariable long id
    ) {
        try {


//            ==1 có thể xóa

            if (!supplierService.check_delete_suplier(id)) {
                return response.generateResponse("không thể xóa", HttpStatus.BAD_REQUEST, false);

            }

            if (!supplierService.delete(id))
                return response.generateResponse("Delete suppliers fail", HttpStatus.BAD_REQUEST, false);
            return response.generateResponse("Delete suppliers Successfully", HttpStatus.OK, true);
        } catch (Exception e) {
            return response.generateResponse("Delete suppliers fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }
}
