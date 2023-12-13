package com.shop.sport.Controller;

import com.shop.sport.Entity.Category;
import com.shop.sport.Response.Response;
import com.shop.sport.Service.CategoryService;
import com.shop.sport.Service.FileUpload;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    Response response = Response.getInstance();
    @Autowired
    private CategoryService categoryService;


    @Autowired
    private FileUpload fileUpload;


    @GetMapping("/getAllCategory")
    public ResponseEntity<Object> getAllCategory() {

        try {
            List<Category> category = categoryService.getAllCategory();

            return response.generateResponse("Get All category Successfully", HttpStatus.OK, category);
        } catch (Exception e) {
            return response.generateResponse("Get All category fail"+e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Object> updateCategory(
            @PathVariable long id,
            @RequestParam(value="name", required = true) String name,
            @RequestParam(value="image", required = false) MultipartFile file
    ) {

        try {
            Category category = categoryService.findById(id).get();
            category.setCategoryName(name);

            if (file != null){


                    fileUpload.deleteFile(category.getPublicId());

                    Map<String, String> upload = fileUpload.uploadFile(file);

                    category.setImageUrl(upload.get("url"));
                    category.setPublicId(upload.get("public_id"));


                category = categoryService.createCategory(category);

                return response.generateResponse("update category Successfully", HttpStatus.OK, category);
            }
            category = categoryService.createCategory(category);
            return response.generateResponse("update category Successfully", HttpStatus.OK, category);
        } catch (Exception e) {
            return response.generateResponse("update category failed"+e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> searchCategoryById(
            @PathVariable("id") long id
    ) {
        try {
            Category category = categoryService.findById(id).get();
            if (category != null){
                return response.generateResponse("find category Successfully", HttpStatus.OK, category);
            }
            return response.generateResponse("find category failed", HttpStatus.BAD_REQUEST, category);
        } catch (Exception e) {
            return response.generateResponse("find category failed"+e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }


//    khi tạo category thì phải chọn những mô tả nổi bật special của category đó
    @PostMapping("/add")
    @Transactional
    public ResponseEntity<Object> createCategory(
            @RequestParam("categoryName") String categoryName,
            @RequestParam(value = "image" , required = true) MultipartFile multipartFile

    ) throws IOException {
        String public_id ="";
        try {

            if (categoryService.isExsitByName(categoryName)) {
                return response.generateResponse("createCategory is Exsit", HttpStatus.OK, categoryName);
            }

            Category category = new Category();
            category.setCategoryName(categoryName);

            Map<String, String> upload =  fileUpload.uploadFile(multipartFile);
            public_id = upload.get("public_id");
            category.setImageUrl(upload.get("url"));
            category.setPublicId(public_id);

            categoryService.createCategory(category);


            return response.generateResponse("createCategory Successfully", HttpStatus.OK, category);
        } catch (Exception e) {
            fileUpload.deleteFile(public_id);
            return response.generateResponse("createCategory fail"+e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }


    @DeleteMapping("/categorys/{id}")
    public ResponseEntity<Object> deleteCategory(
            @PathVariable long id
    ) {
        try {

            //check delete branhd
            if (!categoryService.checkdelete(id)){
                return response.generateResponse("không thể xóa", HttpStatus.BAD_REQUEST, false);
            }

            if (!categoryService.delete_category(id))
                return response.generateResponse("Delete brands fail", HttpStatus.BAD_REQUEST, false);
            return response.generateResponse("Delete brands Successfully", HttpStatus.OK, true);
        } catch (Exception e) {
            return response.generateResponse("Delete brands fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }


}
