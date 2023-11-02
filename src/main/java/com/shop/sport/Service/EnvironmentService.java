package com.shop.sport.Service;

import com.shop.sport.Entity.Brand;
import com.shop.sport.Entity.Environment;
import com.shop.sport.Repositories.IBrandRepository;
import com.shop.sport.Repositories.IEnvironmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnvironmentService {
    @Autowired
    private IEnvironmentRepository iBrandRepository;

    public Environment findById(long id) {
        Optional<Environment> environment = iBrandRepository.findById(id);
        if (environment.isEmpty())
            return null;
        return environment.get();
    }

    public Boolean isExsitByName(String name) {
        if (iBrandRepository.isExsitEnvironment(name)==1)
            return true;
        return false;
    }

    public Boolean isExsit(long id) {
        if (iBrandRepository.findById(id).isEmpty())
            return false;
        return true;
    }
    public List<Environment> getAll() {
        return (List<Environment>) iBrandRepository.findAll();
    }


    public Environment update(long id, String name) {
        Optional<Environment> brand = iBrandRepository.findById(id);
        brand.get().setEnvironment_name(name);
        iBrandRepository.save(brand.get());
        return  brand.get();

    }
    public Environment create(String name) {
        Environment brand = new Environment();
        brand.setEnvironment_name(name);
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
