package com.shop.sport.Controller;

import com.shop.sport.Entity.SpecialDetail;
import com.shop.sport.Response.Response;
import com.shop.sport.Service.SpecialDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/special-detail-management")
public class SpecialDetailController {

    Response response = Response.getInstance();

    @Autowired
    private SpecialDetailService specialDetailService;

    

//    @PostMapping("/special-details")
//    public ResponseEntity<Object> create(@RequestBody Map<String, String> body) {
//        try {
//            String name = body.get("name");
//            if (specialDetailService.isExsitByName(name))
//                return response.generateResponse("special-details is Exsit", HttpStatus.OK, null);
//            SpecialDetail special = specialDetailService.create(name);
//
//            return response.generateResponse("special-details create succesfully", HttpStatus.OK, special);
//        } catch (Exception e) {
//            return response.generateResponse("special-details create fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
//        }
//
//    }
//get special detail by category
    @GetMapping("/special-details/{id_special}")
    public ResponseEntity<Object> getAllBySpecial(
            @PathVariable("id_special") long id_special
    ) {
        try {
            List<SpecialDetail> specialDetails = specialDetailService.getAllBySpecial(id_special);
            return response.generateResponse("Get All special details by id id_special = "+id_special + " Successfully", HttpStatus.OK, specialDetails);
        } catch (Exception e) {
            return response.generateResponse("Get All special details fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }

    @PutMapping("/special-details/{id}")
    public ResponseEntity<Object> update(
            @RequestBody Map<String, String> body,
            @PathVariable("id") long id
    ) {

        try {

            String name = body.get("name");

            SpecialDetail special = specialDetailService.update(id, name);

            return response.generateResponse("update special details Successfully", HttpStatus.OK, special);
        } catch (Exception e) {
            return response.generateResponse("update special details fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }

    @DeleteMapping("/specials/{id}")
    public ResponseEntity<Object> delete(
            @PathVariable long id
    ) {
        try {
            if (!specialDetailService.delete(id))
                return response.generateResponse("Delete special details fail", HttpStatus.BAD_REQUEST, false);
            return response.generateResponse("Delete special details Successfully", HttpStatus.OK, true);
        } catch (Exception e) {
            return response.generateResponse("Delete special details fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }
}
