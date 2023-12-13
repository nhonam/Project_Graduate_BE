package com.shop.sport.Repositories;

import com.shop.sport.Entity.Brand;
import com.shop.sport.Entity.Environment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IEnvironmentRepository extends CrudRepository<Environment, Long> {

    @Query(value = "CALL is_exsit_environment(:name);", nativeQuery = true)
    Long isExsitEnvironment(@Param("name") String name);

    @Query(value = "CALL check_delete_environment(:id);", nativeQuery = true)
    int check_delete_environment(@Param("id") long id);



}
