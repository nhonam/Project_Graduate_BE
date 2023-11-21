package com.shop.sport.Service;

import com.shop.sport.DTO.EvaluateDTO;
import com.shop.sport.Entity.Evaluate;
import com.shop.sport.Entity.Unit;
import com.shop.sport.Repositories.IEvaluateRepository;
import com.shop.sport.Repositories.IUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EvaluateService {
    @Autowired
    private IEvaluateRepository iEvaluateRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    public Evaluate findById(long id) {
        Optional<Evaluate> environment = iEvaluateRepository.findById(id);
        if (environment.isEmpty())
            return null;
        return environment.get();
    }

    public List<EvaluateDTO> getAllEvaluateByIdProduct(long idProduct) {

        return iEvaluateRepository.get_all_evaluate_by_id_product(idProduct);
    }

    public Evaluate creatEvaluate(long idUser, String cmt, long idProduct, long idOrderItem, int start) {
        Evaluate evaluate = new Evaluate();
        evaluate.setUser(userService.getUserById(idUser));
        evaluate.setStart(start);
        evaluate.setComment(cmt);
        evaluate.setOrderItem(orderService.findOrderItemByID(idOrderItem));
        evaluate.setProduct(productService.findProductById(idProduct));
      return  iEvaluateRepository.save(evaluate);

    }

    public Boolean deleteUnit(long id) {
        try {
            iEvaluateRepository.deleteById(id);
            return true;

        }catch (Exception e) {
            return false;
        }


    }
}
