package com.shop.sport.Repositories;

import com.shop.sport.Entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends CrudRepository<Role, Long> {

}
