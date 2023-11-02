package com.shop.sport.Repositories;

import com.shop.sport.DTO.CartDTO;
import com.shop.sport.Entity.CartItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICartItemRepository extends CrudRepository<CartItem, Long> {

    @Query(value = "CALL getCart_by_iduser(:user_id);", nativeQuery = true)
    List<CartDTO> getCart_by_iduser(@Param("user_id") long user_id);



}
