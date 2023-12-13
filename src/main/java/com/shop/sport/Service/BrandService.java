package com.shop.sport.Service;

import com.shop.sport.Entity.Activity;
import com.shop.sport.Entity.Brand;
import com.shop.sport.Repositories.IActivityRepository;
import com.shop.sport.Repositories.IBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {
    @Autowired
    private IBrandRepository iBrandRepository;
    public Boolean isExsitByName(String name) {
        if (iBrandRepository.isExsitBrand(name)==1)
            return true;
        return false;
    }

    public Boolean check_delete_brand(long id) {
        return iBrandRepository.check_delete_brand(id) == 1;
    }

    public Brand findById(long id) {
        Optional<Brand> environment = iBrandRepository.findById(id);
        if (environment.isEmpty())
            return null;
        return environment.get();
    }
    public Boolean isExsit(long id) {
        if (iBrandRepository.findById(id).isEmpty())
            return false;
        return true;
    }
    public List<Brand> getAll() {
        return (List<Brand>) iBrandRepository.findAll();
    }


    public Brand update(long id, String name) {
        Optional<Brand> brand = iBrandRepository.findById(id);
        brand.get().setBrandName(name);
        iBrandRepository.save(brand.get());
        return  brand.get();

    }
    public Brand create(String name) {
        Brand brand = new Brand();
        brand.setBrandName(name);
      return  iBrandRepository.save(brand);

    }

    public Boolean delete(long id) {
        try {
            iBrandRepository.deleteById(id);
            return true;

        }catch (Exception e) {
            return false;
        }


    }
}
