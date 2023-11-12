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
    private ICartItemRepository iCartItemRepository;
    @Async
    public List<CartDTO> getAllCartByUser(long idUser) {
      return  iCartItemRepository.getCart_by_iduser(idUser);

    }

    @Async
    public int add_to_cart(long id_product,long quantity,long id_user) {
        return  iCartItemRepository.add_to_cart(id_product,quantity,id_user);

    }
}
