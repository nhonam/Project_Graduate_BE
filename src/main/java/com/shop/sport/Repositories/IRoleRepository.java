package com.shop.sport.Repositories;

import com.shop.sport.Entity.CartItem;
import com.shop.sport.Entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IRoleRepository extends CrudRepository<Role, Long> {

    @Override
    Optional<Role> findById(Long aLong);
}
