package com.shop.sport.Service;

import com.shop.sport.Entity.Activity;
import com.shop.sport.Entity.Unit;
import com.shop.sport.Repositories.IActivityRepository;
import com.shop.sport.Repositories.IUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitService {
    @Autowired
    private IUnitRepository iUnitRepository;

    public Boolean isExsitActivity(String activityName) {
        if (iUnitRepository.isExsitUnit(activityName)== 1)
            return true;
        return false;
    }

    public Unit findById(long id) {
        Optional<Unit> environment = iUnitRepository.findById(id);
        if (environment.isEmpty())
            return null;
        return environment.get();
    }

    public List<Unit> getAllUnit() {
        return (List<Unit>) iUnitRepository.findAll();
    }


    public Unit updateUnit(long id, String nameActivity) {
        Optional<Unit> activity = iUnitRepository.findById(id);
        activity.get().setUnitName(nameActivity);
        iUnitRepository.save(activity.get());
        return  activity.get();

    }
    public Unit createUnit(String activityName) {
        Unit activity = new Unit();
        activity.setUnitName(activityName);
      return  iUnitRepository.save(activity);

    }

    public Boolean deleteUnit(long id) {
        try {
            iUnitRepository.deleteById(id);
            return true;

        }catch (Exception e) {
            return false;
        }


    }
}
