package com.shop.sport.Controller;

import com.shop.sport.Entity.Brand;
import com.shop.sport.Entity.Category;
import com.shop.sport.Entity.Special;
import com.shop.sport.Entity.SpecialDetail;
import com.shop.sport.Response.Response;
import com.shop.sport.Service.BrandService;
import com.shop.sport.Service.CategoryService;
import com.shop.sport.Service.SpecialDetailService;
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

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SpecialDetailService specialDetailService;

    

    @PostMapping("/specials")
    public ResponseEntity<Object> create(@RequestBody Map<String, String> body) {
        try {
            String name_special = body.get("name_special");
            if (specialService.isExsitByName(name_special))
                return response.generateResponse("special is Exsit", HttpStatus.OK, null);
            Special special = specialService.create(name_special);
            String name_special_detail_f = body.get("name_special_detail_f");
            String name_special_detail_s = body.get("name_special_detail_s");
            String name_special_detail_t = body.get("name_special_detail_t");
            String name_special_detail_fo = body.get("name_special_detail_fo");

            if (!name_special_detail_f.equalsIgnoreCase(""))
                specialDetailService.create(name_special_detail_f,special.getId());
            if (!name_special_detail_s.equalsIgnoreCase(""))
                specialDetailService.create(name_special_detail_s,special.getId());
            if (!name_special_detail_t.equalsIgnoreCase(""))
                specialDetailService.create(name_special_detail_t,special.getId());
            if (!name_special_detail_fo.equalsIgnoreCase(""))
                specialDetailService.create(name_special_detail_fo,special.getId());

            return response.generateResponse("special and special_detail succesfully", HttpStatus.OK, special);
        } catch (Exception e) {
            return response.generateResponse("special and special_detail fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
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

    @GetMapping("/specials-by-category/{id}")
    public ResponseEntity<Object> getSpecialsByIdCategory(
            @PathVariable("id") long id
    ) {

        try {

            Category category = categoryService.findById(id).get();

            return response.generateResponse("Get All special by id category Successfully", HttpStatus.OK, category.getSpecials().toArray());
        } catch (Exception e) {
            return response.generateResponse("Get All special by id category fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
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
