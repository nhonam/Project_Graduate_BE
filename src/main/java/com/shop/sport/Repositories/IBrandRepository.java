package com.shop.sport.Repositories;

import com.shop.sport.Entity.Activity;
import com.shop.sport.Entity.Brand;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IBrandRepository extends CrudRepository<Brand, Long> {

    @Query(value = "CALL is_exsit_brand(:name);", nativeQuery = true)
    Long isExsitBrand(@Param("name") String name);



}
