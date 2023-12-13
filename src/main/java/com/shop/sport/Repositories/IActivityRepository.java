package com.shop.sport.Repositories;

import com.shop.sport.Entity.Activity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IActivityRepository extends CrudRepository<Activity, Long> {

    @Query(value = "CALL is_exsit_activity(:activity_name);", nativeQuery = true)
    Long isExsitActivity(@Param("activity_name") String activity_name);


    @Query(value = "CALL check_delete_activity(:id);", nativeQuery = true)
    int check_delete_activity(@Param("id") long id);
}
