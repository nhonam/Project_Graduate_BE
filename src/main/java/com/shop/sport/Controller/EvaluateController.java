package com.shop.sport.Controller;

import com.shop.sport.Entity.Evaluate;
import com.shop.sport.Entity.Unit;
import com.shop.sport.Response.Response;
import com.shop.sport.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/evaluate-management")
@CrossOrigin(origins = "http://localhost:3000")
public class EvaluateController {

    Response response = Response.getInstance();

    @Autowired
    private EvaluateService evaluateService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/evaluates")
    public ResponseEntity<Object> createEvaluate(@RequestBody Map<String, String> body) {
        try {
            String comment = body.get("comment");
            int star = Integer.parseInt(body.get("star"));
            long id_user = Long.parseLong(body.get("id_user"));
            long id_order_item = Long.parseLong(body.get("id_order_item"));
            long id_product = Long.parseLong(body.get("id_product"));

            return response.generateResponse("create units succesfully", HttpStatus.OK, evaluateService.creatEvaluate( id_user,comment,id_product,id_order_item,star));
        } catch (Exception e) {
            return response.generateResponse("create units fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }

    @GetMapping("/evaluates/{id_product}")
    public ResponseEntity<Object> getAllActivity(
            @PathVariable("id_product") long id_product
    ) {

        try {
            List<Evaluate> evaluates = evaluateService.getAllEvaluateByIdProduct(id_product);

            return response.generateResponse("Get All evaluates by id Product Successfully", HttpStatus.OK, evaluates);
        } catch (Exception e) {
            return response.generateResponse("Get All  evaluates by id Product fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }

//    @PutMapping("/evaluates/{id}")
//    public ResponseEntity<Object> updateActivity(
//            @RequestBody Map<String, String> body,
//            @PathVariable long id
//    ) {
//        try {
//
//            String name = body.get("name");
//
//            Unit activity = evaluateService.(id, name);
//
//            return response.generateResponse("Get All units Successfully", HttpStatus.OK, activity);
//        } catch (Exception e) {
//            return response.generateResponse("Get All units fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
//        }
//
//    }

    @DeleteMapping("/units/{id}")
    public ResponseEntity<Object> deleteActivity(
            @PathVariable long id
    ) {
        try {
            if (!evaluateService.deleteUnit(id))
                return response.generateResponse("Delete units fail", HttpStatus.BAD_REQUEST, false);
            return response.generateResponse("Delete units Successfully", HttpStatus.OK, true);
        } catch (Exception e) {
            return response.generateResponse("Delete units fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }
}
