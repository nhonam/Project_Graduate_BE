package com.shop.sport.Repositories;

import com.shop.sport.Entity.Activity;
import com.shop.sport.Entity.Special;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ISpecialRepository extends CrudRepository<Special, Long> {

    @Query(value = "CALL is_exsit_special(:name);", nativeQuery = true)
    Long isExsitSpecial(@Param("name") String name);


}
