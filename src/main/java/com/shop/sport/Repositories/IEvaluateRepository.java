package com.shop.sport.Repositories;

import com.shop.sport.DTO.EvaluateDTO;
import com.shop.sport.DTO.OrderDTO;
import com.shop.sport.Entity.Evaluate;
import com.shop.sport.Entity.Unit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEvaluateRepository extends CrudRepository<Evaluate, Long> {


    @Query(value = "CALL get_all_evaluate_by_id_product(:id_product)", nativeQuery = true)
    List<EvaluateDTO> get_all_evaluate_by_id_product(@Param("id_product") long id_product);

}
