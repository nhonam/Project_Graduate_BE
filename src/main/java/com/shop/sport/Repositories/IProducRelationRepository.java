package com.shop.sport.Repositories;

import com.shop.sport.Entity.ProductRelation;
import com.shop.sport.Entity.Special;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IProducRelationRepository extends CrudRepository<ProductRelation, Long> {

    @Query(value = "CALL is_exsit_product_relation(:id_cur, :id_tar);", nativeQuery = true)
    Long is_exsit_product_relation(@Param("id_cur") int id_cur, @Param("id_tar") int id_tar );


}
