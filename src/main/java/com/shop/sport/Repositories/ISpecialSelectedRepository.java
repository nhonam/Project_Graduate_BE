package com.shop.sport.Repositories;

import com.shop.sport.Entity.SpecialSelected;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISpecialSelectedRepository extends CrudRepository<SpecialSelected, Long> {

//    @Query(value = "CALL is_exsit_special_detail(:name);", nativeQuery = true)
//    Long isExsitSpecialDetail(@Param("name") String name);


}
