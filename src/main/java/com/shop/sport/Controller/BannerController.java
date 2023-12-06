package com.shop.sport.Controller;

import com.shop.sport.Entity.Banner;
import com.shop.sport.Entity.Category;
import com.shop.sport.Entity.Product;
import com.shop.sport.Response.Response;
import com.shop.sport.Service.*;
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
@RequestMapping("/api/v1/banner")
public class BannerController {
    Response response = Response.getInstance();
    @Autowired
    private BannerService bannerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private FileUpload fileUpload;

    @GetMapping("/five")
    public ResponseEntity<Object> getFiveCategory() {

        try {
            List<Banner> category = bannerService.getFiveBanner();

            return response.generateResponse("Get All Banner Successfully", HttpStatus.OK, category);
        } catch (Exception e) {
            return response.generateResponse("Get All Banner fail"+e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }


    @GetMapping("/all")
    public ResponseEntity<Object> getAllCategory() {

        try {
            List<Banner> category = bannerService.getAllBanner();

            return response.generateResponse("Get All Banner Successfully", HttpStatus.OK, category);
        } catch (Exception e) {
            return response.generateResponse("Get All Banner fail"+e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Object> updateCategory(
            @PathVariable long id,
            @RequestParam(value="idProduct", required = false) long idProduct,
            @RequestParam(value="image", required = false) MultipartFile file
    ) {

        try {
            Banner category = bannerService.findById(id).get();
            Product product = productService.findProductById(idProduct);
            category.setProduct(product);


                if (file != null) {
                    fileUpload.deleteFile(category.getPublic_id());

                    Map<String, String> upload = fileUpload.uploadFile(file);

                    category.setUrl_banner(upload.get("url"));
                    category.setPublic_id(upload.get("public_id"));
                }
                bannerService.createBanner(category);

                return response.generateResponse("update banner Successfully", HttpStatus.OK, category);


        } catch (Exception e) {
            return response.generateResponse("update banner failed"+e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> searchCategoryById(
            @PathVariable("id") long id
    ) {
        try {
            Banner category = bannerService.findById(id).get();
            if (category != null){
                return response.generateResponse("find Banner Successfully", HttpStatus.OK, category);
            }
            return response.generateResponse("find Banner failed", HttpStatus.BAD_REQUEST, category);
        } catch (Exception e) {
            return response.generateResponse("find Banner failed"+e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }


//    khi tạo category thì phải chọn những mô tả nổi bật special của category đó
    @PostMapping("/add")
    public ResponseEntity<Object> createCategory(
            @RequestParam("idProduct") long idProduct,
            @RequestParam(value = "image" , required = true) MultipartFile multipartFile

    ) throws IOException {
        String public_id ="";
        try {

            Banner category = new Banner();
            category.setProduct(productService.getProductById(idProduct).get());

            Map<String, String> upload =  fileUpload.uploadFile(multipartFile);
            public_id = upload.get("public_id");
            category.setUrl_banner(upload.get("url"));
            category.setPublic_id(public_id);

            bannerService.createBanner(category);


            return response.generateResponse("create banner Successfully", HttpStatus.OK, category);
        } catch (Exception e) {
            fileUpload.deleteFile(public_id);
            return response.generateResponse("create banner fail"+e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }


}
