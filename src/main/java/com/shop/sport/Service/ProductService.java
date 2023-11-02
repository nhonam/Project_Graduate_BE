package com.shop.sport.Service;

import com.shop.sport.Entity.Product;
import com.shop.sport.Repositories.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private IProductRepo iProductRepo;

    public List<Product> getAllProduct() {
        return iProductRepo.findAllProduct();
    }

    public Long checkDelete(long id) {
        Long result = iProductRepo.check_delete_product(id);
        return result;
    }

    public int CreateProduct(String product_name,int stock_quantity,float price,String description,String image_url,String public_id,
            int category_id,int id_environment,int id_supplier,int id_activity,int id_brand,String id_special_details) {
        return iProductRepo.AddProduct(product_name,stock_quantity,price,description,image_url,public_id,
        category_id,id_environment,id_supplier,id_activity,id_brand,id_special_details);
    }

    public List<Product> bestSell() {
        return iProductRepo.bestSellProduct();
    }

    public List<Product> getAllProductByCategory(String categoryName) {
        return iProductRepo.getAllProductByCategory(categoryName);
    }

    public List<Product> searchProduct(String text) {
        return iProductRepo.searchProduct(text);
    }

    public Optional<Product> getProductById(long id) {
//        if (id==0) return null;
        return iProductRepo.findById(id);
    }

    public Boolean DeleteProduct(long id) {
        try {
            iProductRepo.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }

    }

    public Product createProduct(Product product) {
//        if(user.getRole().equals("ADMIN"))
//            user.setRole(RoleEnum.ADMIN);
//        else user.setRole(RoleEnum.EMPLOYEE);
//        String passWord = passwordEncoder.encode(user.getPassword());
//        user.setPassword(passWord);
        return iProductRepo.save(product);
    }
}
