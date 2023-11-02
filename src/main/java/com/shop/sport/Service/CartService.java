package com.shop.sport.Service;

import com.shop.sport.DTO.CartDTO;
import com.shop.sport.Entity.CartItem;
import com.shop.sport.Repositories.ICartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private ICartItemRepository getCart_by_iduser;
    @Async
    public List<CartDTO> getAllCartByUser(long idUser) {

      return  getCart_by_iduser.getCart_by_iduser(idUser);

    }
}
