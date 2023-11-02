package com.shop.sport.Service;

import com.shop.sport.Entity.Category;
import com.shop.sport.Repositories.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {



    @Autowired
    private ICategoryRepository iCategoryRepository;

    public Boolean isExsitByName(String name) {
        if (iCategoryRepository.is_exsit_category(name)==1)
            return true;
        return false;
    }

    public List<Category> getAllCategory() {
        return (List<Category>) iCategoryRepository.findAll();
    }

    public Optional<Category> findById(long id) {
        return iCategoryRepository.findById(id);
    }


    public Category createCategory(Category category) {

        return iCategoryRepository.save(category);
    }
}
