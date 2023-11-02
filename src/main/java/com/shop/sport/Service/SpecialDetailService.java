package com.shop.sport.Service;

import com.shop.sport.Entity.Special;
import com.shop.sport.Entity.SpecialDetail;
import com.shop.sport.Repositories.ISpeciaDetaillRepository;
import com.shop.sport.Repositories.ISpecialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SpecialDetailService {
    @Autowired
    private ISpeciaDetaillRepository iSpeciaDetaillRepository;

    @Autowired
    private SpecialService specialService;
    public Boolean isExsitByName(String name) {
        if (iSpeciaDetaillRepository.isExsitSpecialDetail(name)==1)
            return true;
        return false;
    }

    public SpecialDetail findById(long id) {

        SpecialDetail  specialDetail = iSpeciaDetaillRepository.findById(id).get();
        return specialDetail;
    }
    public Boolean isExsit(long id) {
        if (iSpeciaDetaillRepository.findById(id).isEmpty())
            return false;
        return true;
    }
    public List<SpecialDetail> getAllBySpecial(long idSpecial) {

        List<SpecialDetail>  list = (List<SpecialDetail>) iSpeciaDetaillRepository.findAll();
        List<SpecialDetail> listTmp = new ArrayList<SpecialDetail>();
        for (SpecialDetail item:
                list ) {
            if (item.getSpecial().getId() == idSpecial) {
                listTmp.add(item);
            }
        }

        return listTmp;
    }


    public SpecialDetail update(long id, String name) {
        Optional<SpecialDetail> brand = iSpeciaDetaillRepository.findById(id);
        brand.get().setSpecialDetailName(name);
        iSpeciaDetaillRepository.save(brand.get());
        return  brand.get();

    }
    public SpecialDetail create(String name, long idSpecial) {
        SpecialDetail brand = new SpecialDetail();
        brand.setSpecialDetailName(name);
        brand.setSpecial(specialService.findById(idSpecial));
      return  iSpeciaDetaillRepository.save(brand);

    }

    public Boolean delete(long id) {
        try {
            iSpeciaDetaillRepository.deleteById(id);
            return true;

        }catch (Exception e) {
            return false;
        }


    }
}
