package com.shop.sport.Service;

import com.shop.sport.DTO.SpecialDTO;
import com.shop.sport.Entity.Product;
import com.shop.sport.Repositories.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private IProductRepo iProductRepo;

    public Product oneProductByName(String nameProduct){
       return iProductRepo.find_product_by_name(nameProduct);
    }

    public List<Product> getAllProduct() {
        return iProductRepo.findAllProduct();
    }

    public Product findProductById(long id) {
        return iProductRepo.findById(id).get();
    }

    public Long checkDelete(long id) {
        Long result = iProductRepo.check_delete_product(id);
        return result;
    }

    public int CreateProduct(String product_name,int stock_quantity,float price,String description,String image_url,String public_id,
            int category_id,int id_environment,int id_supplier,int id_activity,int id_brand,int id_unit) {
        return iProductRepo.AddProduct(product_name,stock_quantity,price,description,image_url,public_id,
        category_id,id_environment,id_supplier,id_activity,id_brand,id_unit);
    }

    public List<Product> bestSell() {
        return iProductRepo.bestSellProduct();
    }

    public List<SpecialDTO> getSpecialDTO(long id) {
        return iProductRepo.getProductByIDSP(id);
    }

    public List<Product> getAllProductByCategory(String categoryName) {
        return iProductRepo.getAllProductByCategory(categoryName);
    }

    public List<Product> searchProductByText(String text) {
        return iProductRepo.searchProduct(text);
    }

    public int isExsitProduct(String text) {
        return iProductRepo.is_exsit_product(text);
    }

    public long QuantiProductSell(long idProduct) {
        return iProductRepo.get_quanti_bought_by_idProduct(idProduct);
    }

    public int getStarByIdProduct(long idProduct) {
        return (int) iProductRepo.get_star_by_id_product(idProduct);
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
