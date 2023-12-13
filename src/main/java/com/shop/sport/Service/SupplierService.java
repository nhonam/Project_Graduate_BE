package com.shop.sport.Service;

import com.shop.sport.Entity.Environment;
import com.shop.sport.Entity.Supplier;
import com.shop.sport.Repositories.IEnvironmentRepository;
import com.shop.sport.Repositories.ISupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    private ISupplierRepository iBrandRepository;

    public Boolean isExsit(String name) {
        if (iBrandRepository.isExsitSupplier( name) == 1)
            return true;
        return false;
    }

    public Boolean check_delete_suplier(long id) {
        if (iBrandRepository.check_delete_suplier( id) ==1 )
            return true;
        return false;
    }

    public Supplier findById(long id) {
        Optional<Supplier> environment = iBrandRepository.findById(id);
        if (environment.isEmpty())
            return null;
        return environment.get();
    }

    public List<Supplier> getAll() {
        return (List<Supplier>) iBrandRepository.findAll();
    }


    public Supplier update(long id, String name) {
        Optional<Supplier> brand = iBrandRepository.findById(id);
        brand.get().setSupplierName(name);
        iBrandRepository.save(brand.get());
        return  brand.get();

    }
    public Supplier create(String name) {
        Supplier brand = new Supplier();
        brand.setSupplierName(name);
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
