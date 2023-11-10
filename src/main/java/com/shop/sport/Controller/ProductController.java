package com.shop.sport.Controller;

import com.shop.sport.Entity.Product;
import com.shop.sport.Response.Response;
import com.shop.sport.Service.FileUpload;
import com.shop.sport.Service.ProductService;
import com.shop.sport.Service.SpecialDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    Response response = Response.getInstance();
    @Autowired
    private FileUpload fileUpload;
    @Autowired
    private ProductService productService;

    @Autowired
    private SpecialDetailService specialDetailService;


    @GetMapping("/allProduct")
    public ResponseEntity<Object> getAllProduct() {
        try {
            List<Product> list = productService.getAllProduct();

            return response.generateResponse("Get All product Successfully", HttpStatus.OK, list);

        } catch (Exception e) {
            return response.generateResponse("Get All product fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);

        }
    }


    @GetMapping("/best_sell")
    public ResponseEntity<Object> bestSellProduct() {
        try {
            List<Product> list = productService.bestSell();

            return response.generateResponse("best_sell product Successfully", HttpStatus.OK, list);

        } catch (Exception e) {
            return response.generateResponse("best_sell product fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);

        }
    }


    @GetMapping("/byCategory")
    public ResponseEntity<Object> getProductbyCategory(
            @RequestParam("categoryName") String category
    ) {
        try {
            List<Product> list = productService.getAllProductByCategory(category);

            return response.generateResponse("getProductbyCategory Successfully", HttpStatus.OK, list);

        } catch (Exception e) {
            return response.generateResponse("getProductbyCategory product fail in ProductController" + e.getMessage(), HttpStatus.BAD_REQUEST, null);

        }
    }

    @GetMapping("/byText")
    public ResponseEntity<Object> searchProductsearchProduct(
            @RequestParam("text") String text
    ) {
        try {
            List<Product> list = productService.searchProductByText(text);

            return response.generateResponse("getProductbyCategory Successfully", HttpStatus.OK, list);

        } catch (Exception e) {
            return response.generateResponse("getProductbyCategory product fail in ProductController" + e.getMessage(), HttpStatus.BAD_REQUEST, null);

        }
    }

    @PostMapping("/add")
    public ResponseEntity<Object> createProduct(
            @RequestParam("stockQuantity") int stockQuantity,
            @RequestParam("price") float price,
            @RequestParam("description") String description,
            @RequestParam("productName") String productName,
            @RequestParam("category_id") long id_category,
            @RequestParam("id_environment") long id_environment,
            @RequestParam("id_supplier") long id_supplier,
            @RequestParam("id_activity") long id_activity,
            @RequestParam("id_brand") long id_brand,
            @RequestParam("id_unit") long id_unit,
            @RequestParam("id_special_details") String id_special_details,
            @RequestParam(value = "image") MultipartFile multipartFile

    ) throws IOException {
        String public_id = "";
        try {
            if (productService.isExsitProduct(productName) == 1) {
                return response.generateResponse(" product is exsit", HttpStatus.OK, "done");
            }

            Map<String, String> upload = fileUpload.uploadFile(multipartFile);
            public_id = upload.get("public_id");

            if (productService.CreateProduct(productName, stockQuantity, price, description, upload.get("url"), public_id,
                    (int) id_category, (int) id_environment, (int) id_supplier, (int) id_activity, (int) id_brand, (int) id_unit,id_special_details) ==1)
                return response.generateResponse("create product Successfully", HttpStatus.OK, "done");

            fileUpload.deleteFile(public_id);
            return response.generateResponse("create product failed (producer return fail) ProductController", HttpStatus.OK, null);

        } catch (Exception e) {
            if (public_id != "")
                fileUpload.deleteFile(public_id);
            return response.generateResponse("create product failed ProductController" + e.getMessage(), HttpStatus.BAD_REQUEST, null);

        }

    }

    @GetMapping("/one/{id}")
    public ResponseEntity<Object> getProductByid(
            @PathVariable("id") long id
    ) {
        try {
            Product product = productService.getProductById(id).get();
            if (product == null)
                return response.generateResponse("product not found", HttpStatus.BAD_REQUEST, null);
            Map<String, Object> data = new HashMap<>();
            data.put("product", product);
            data.put("special", productService.getSpecialDTO(product.getId()));



                   return response.generateResponse(" product successfully", HttpStatus.OK, data);

        } catch (Exception e) {
            return response.generateResponse(" product failed", HttpStatus.OK, null);

        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Object> deleteProduct(
            @PathVariable long id
    ) {

        try {

            Product product = productService.getProductById(id).get();


            if (product == null)
                return response.generateResponse("product not found", HttpStatus.BAD_REQUEST, null);

            long isDel = productService.checkDelete(id);
            if (isDel == 1) {
                product.setStatus(false);
//            fileUpload.deleteFile(product.getPublicId());
                productService.createProduct(product);
                return response.generateResponse("delete product successfully", HttpStatus.OK, isDel);
            }
            return response.generateResponse("product exist in cart or order !!! delete failer", HttpStatus.OK, null);

        } catch (Exception e) {
            return response.generateResponse("delete product failed" + e.getMessage(), HttpStatus.OK, null);

        }
    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Object> deleteProductReal(
//            @PathVariable long id
//    ) {
//
//        try {
//            Boolean isDel = productService.DeleteProduct(id);
//            if (isDel)
//                return response.generateResponse("Delete Product sucess", HttpStatus.OK, isDel);
//            return response.generateResponse("delete product failed", HttpStatus.BAD_REQUEST, null);
//
//        } catch (Exception e) {
//            return response.generateResponse("delete product failed", HttpStatus.BAD_REQUEST, null);
//
//        }
//    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Object> updateProduct(
            @PathVariable long id,
            @RequestParam(value = "stockQuantity", required = false) String stockQuantity,
            @RequestParam(value = "price", required = false) String price,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "productName", required = false) String productName,
//            @RequestParam(value = "category_id", required = false) String category_id,
            @RequestParam(value = "image", required = false) MultipartFile multipartFile

    ) {

        try {

            Product product = productService.getProductById(id).get();
            if (!stockQuantity.isEmpty()) {
                product.setStockQuantity(Integer.parseInt(stockQuantity));
            }
            if (!price.isEmpty()) {
                product.setPrice(Float.parseFloat(price));
            }
            if (!description.isEmpty()) {
                product.setDescription(description);
            }
            if (!productName.isEmpty()) {
                product.setProductName(productName);
            }
            if (multipartFile != null) {

                fileUpload.deleteFile(product.getPublicId());

                Map<String, String> upload = fileUpload.uploadFile(multipartFile);

                product.setImageUrl(upload.get("url"));
                product.setPublicId(upload.get("public_id"));
            }

            System.out.println(product.getCategory().getId() + " nânnnaa");


            product = productService.createProduct(product);
            return response.generateResponse("update product Successfully", HttpStatus.OK, product);

        } catch (Exception e) {
            return response.generateResponse("update product failed ProductController" + e.getMessage(), HttpStatus.BAD_REQUEST, null);

        }

    }


}
