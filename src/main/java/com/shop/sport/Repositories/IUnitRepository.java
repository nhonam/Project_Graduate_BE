package com.shop.sport.Repositories;

import com.shop.sport.Entity.Activity;
import com.shop.sport.Entity.Unit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUnitRepository extends CrudRepository<Unit, Long> {

    @Query(value = "CALL is_exsit_unit(:unit_name);", nativeQuery = true)
    Long isExsitUnit(@Param("unit_name") String unit_name);


}
