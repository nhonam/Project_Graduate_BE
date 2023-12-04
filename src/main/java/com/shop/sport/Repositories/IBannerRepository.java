package com.shop.sport.Repositories;

import com.shop.sport.Entity.Banner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBannerRepository extends CrudRepository<Banner, Long> {


}
