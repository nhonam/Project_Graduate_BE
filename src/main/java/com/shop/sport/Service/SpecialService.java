package com.shop.sport.Service;

import com.shop.sport.Entity.Category;
import com.shop.sport.Entity.Special;
import com.shop.sport.Repositories.ISpecialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialService {
    @Autowired
    private ISpecialRepository iSpecialRepository;

    @Autowired
    private CategoryService categoryService;

    public Special findById(long id) {
        Optional<Special> special = iSpecialRepository.findById(id);
        if (special.isEmpty())
            return null;
        return special.get();
    }
    public Boolean isExsitByName(String name) {
        if (iSpecialRepository.isExsitSpecial(name)==1)
            return true;
        return false;
    }
    public Boolean isExsit(long id) {
        if (iSpecialRepository.findById(id).isEmpty())
            return false;
        return true;
    }
    public List<Special> getAll() {
        return (List<Special>) iSpecialRepository.findAll();
    }


    public Special update(long id, String name) {
        Optional<Special> brand = iSpecialRepository.findById(id);
        brand.get().setNameSpecial(name);
        iSpecialRepository.save(brand.get());
        return  brand.get();

    }

    public Special updateIdCategorySpecial(long id, long idCategory) {
        Optional<Special> special = iSpecialRepository.findById(id);
        special.get().setCategory(categoryService.findById(idCategory).get());
        iSpecialRepository.save(special.get());
        return  special.get();

    }
    public Special create(String name) {
        Special brand = new Special();
        brand.setNameSpecial(name);
      return  iSpecialRepository.save(brand);

    }

    public Boolean delete(long id) {
        try {
            iSpecialRepository.deleteById(id);
            return true;

        }catch (Exception e) {
            return false;
        }


    }
}
