package com.shop.sport.Repositories;

import com.shop.sport.Entity.OrderStatus;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
@EnableJpaRepositories
public interface IOrderStatusRepo extends CrudRepository<OrderStatus, Long> {
}
