package com.shop.sport.Repositories;

import com.shop.sport.Entity.Banner;
import com.shop.sport.Entity.ProductRelation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBannerRepository extends CrudRepository<Banner, Long> {


}
