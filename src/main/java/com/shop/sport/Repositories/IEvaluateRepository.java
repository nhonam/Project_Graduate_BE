package com.shop.sport.Repositories;

import com.shop.sport.Entity.Evaluate;
import com.shop.sport.Entity.Unit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IEvaluateRepository extends CrudRepository<Evaluate, Long> {
}
