package com.shop.sport.Repositories;

import com.shop.sport.Entity.Banner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IBannerRepository extends CrudRepository<Banner, Long> {

    @Query(value = "CALL check_delete_banner();", nativeQuery = true)
    int check_delete_banner();
}
