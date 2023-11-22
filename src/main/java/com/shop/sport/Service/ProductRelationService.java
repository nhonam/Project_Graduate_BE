package com.shop.sport.Service;

import com.shop.sport.Entity.ProductRelation;
import com.shop.sport.Repositories.IProducRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductRelationService {
    @Autowired
    private IProducRelationRepository iProducRelationRepository;

    @Autowired
    private ProductService productService;
    public Boolean isExsit(int id_cur, int id_tar) {
        if (iProducRelationRepository.is_exsit_product_relation(id_cur, id_tar)==1)
            return true;
        return false;
    }
    public Boolean isExsitById(long id) {
        if (iProducRelationRepository.findById(id).isEmpty())
            return false;
        return true;
    }
    public List<ProductRelation> getAll() {
        return (List<ProductRelation>) iProducRelationRepository.findAll();
    }


    public ProductRelation update(long id, long id_cur, long id_tar) {
        Optional<ProductRelation> brand = iProducRelationRepository.findById(id);
        brand.get().setProductTarget(productService.getProductById(id_tar).get());
        brand.get().setProductCurent(productService.getProductById(id_cur).get());
        iProducRelationRepository.save(brand.get());
        return  brand.get();

    }
    public ProductRelation create(long id_cur, long id_ta) {
        ProductRelation brand = new ProductRelation();
        brand.setProductTarget(productService.getProductById(id_ta).get());
        brand.setProductCurent(productService.getProductById(id_cur).get());
      return  iProducRelationRepository.save(brand);

    }

    public Boolean delete(long id) {
        try {
            iProducRelationRepository.deleteById(id);
            return true;

        }catch (Exception e) {
            return false;
        }


    }
}
