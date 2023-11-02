package com.shop.sport.Repositories;

import com.shop.sport.Entity.Special;
import com.shop.sport.Entity.SpecialDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ISpeciaDetaillRepository extends CrudRepository<SpecialDetail, Long> {

    @Query(value = "CALL is_exsit_special_detail(:name);", nativeQuery = true)
    Long isExsitSpecialDetail(@Param("name") String name);


}
