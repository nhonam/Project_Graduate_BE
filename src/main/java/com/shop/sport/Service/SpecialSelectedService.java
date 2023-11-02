package com.shop.sport.Service;

import com.shop.sport.Entity.SpecialSelected;
import com.shop.sport.Repositories.ISpecialSelectedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Async
public class SpecialSelectedService {
    @Autowired
    private ISpecialSelectedRepository iSpecialSelectedRepository  ;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SpecialDetailService specialDetailService;


    public Boolean isExsitById(long id) {
        if (iSpecialSelectedRepository.findById(id).isEmpty())
            return false;
        return true;
    }
    public List<SpecialSelected> getAll() {
        return (List<SpecialSelected>) iSpecialSelectedRepository.findAll();
    }


    public SpecialSelected update(long id, String name) {
        Optional<SpecialSelected> brand = iSpecialSelectedRepository.findById(id);
//        brand.get().setProduct(productService.getProductById());
//        brand.get().setSpecial(name);
//        brand.get().setCategory(name);
        iSpecialSelectedRepository.save(brand.get());
        return  brand.get();

    }

    @Async
    public SpecialSelected create(long idCategory, long idProduct, long idSpecialDetail) {
        SpecialSelected productSpec = new SpecialSelected();
        productSpec.setSpecialDetail(specialDetailService.findById(idSpecialDetail));
        productSpec.setProduct(productService.getProductById(idProduct).get());
      return  iSpecialSelectedRepository.save(productSpec);

    }

    public Boolean delete(long id) {
        try {
            iSpecialSelectedRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }


    }
}
