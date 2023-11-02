package com.shop.sport.Repositories;

import com.shop.sport.Entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ICategoryRepository extends CrudRepository<Category, Long> {

    @Query(value = "CALL is_exsit_category(:name);", nativeQuery = true)
    Long is_exsit_category(@Param("name") String name);


}
