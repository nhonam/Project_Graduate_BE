package com.shop.sport.Repositories;


import com.shop.sport.DTO.SpecialDTO;
import com.shop.sport.Entity.Product;
import com.shop.sport.Entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@EnableJpaRepositories
public interface IProductRepo extends CrudRepository<Product, Long> {

    @Query(value = "SELECT u FROM Product u WHERE u.status = true and u.isDelete = false ")
    List<Product> findAllProduct();

    @Query(value = "SELECT u FROM Product u where u.isDelete= false ")
    List<Product> findAllProductAdmin();

    @Query(value = "CALL check_delete_product(:idProduct);", nativeQuery = true)
    Long check_delete_product(@Param("idProduct") long id);

    @Query(value = "CALL find_product_by_name(:name);", nativeQuery = true)
    Product find_product_by_name(@Param("name") String name);

    @Query(value = "CALL AddProduct(:product_name,:stock_quantity,:price,:description,:image_url,:public_id," +
            ":category_id,:id_environment,:id_supplier,:id_activity,:id_brand, :id_unit);", nativeQuery = true)
    int AddProduct(@Param("product_name") String product_name,
                    @Param("stock_quantity") int stock_quantity,
                    @Param("price") float price,
                    @Param("description") String description,
                    @Param("image_url") String image_url,
                    @Param("public_id") String public_id,
                    @Param("category_id") int category_id,
                    @Param("id_environment") int id_environment,
                    @Param("id_supplier") int id_supplier,
                    @Param("id_activity") int id_activity,
                    @Param("id_brand") int id_brand,
                    @Param("id_unit") int id_unit
                   );
    @Query(value = "CALL best_sell_product();", nativeQuery = true)
    List<Product> bestSellProduct();

    @Query(value = "CALL get_product_by_id(:id);", nativeQuery = true)
    List<SpecialDTO> getProductByIDSP(@Param("id") long id);


    @Query(value = "CALL is_exsit_product(:text);", nativeQuery = true)
    int is_exsit_product(@Param("text") String text);

    @Query(value = "CALL get_quanti_bought_by_idProduct(:id);", nativeQuery = true)
    long get_quanti_bought_by_idProduct(@Param("id") long id);

    @Query(value = "CALL get_star_by_id_product(:id);", nativeQuery = true)
    float get_star_by_id_product(@Param("id") long id);

    @Query(value = "CALL search_product(:text);", nativeQuery = true)
    List<Product> searchProduct(@Param("text") String text);
    @Query(value = "CALL getAllProductByCategory(:categoryName);", nativeQuery = true)
    List<Product> getAllProductByCategory(@Param("categoryName") String categoryName);
}
