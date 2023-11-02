package com.shop.sport.Service;

import com.shop.sport.DTO.CartDTO;
import com.shop.sport.Entity.Activity;
import com.shop.sport.Entity.Category;
import com.shop.sport.Entity.Environment;
import com.shop.sport.Repositories.IActivityRepository;
import com.shop.sport.Repositories.ICartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {
    @Autowired
    private IActivityRepository iActivityRepository;

    public Boolean isExsitActivity(String activityName) {
        if (iActivityRepository.isExsitActivity(activityName)== 1)
            return true;
        return false;
    }

    public Activity findById(long id) {
        Optional<Activity> environment = iActivityRepository.findById(id);
        if (environment.isEmpty())
            return null;
        return environment.get();
    }

    public List<Activity> getAllActivity() {
        return (List<Activity>) iActivityRepository.findAll();
    }


    public Activity updateActivity(long id, String nameActivity) {
        Optional<Activity> activity = iActivityRepository.findById(id);
        activity.get().setActivityName(nameActivity);
        iActivityRepository.save(activity.get());
        return  activity.get();

    }
    public Activity createActivity(String activityName) {
        Activity activity = new Activity();
        activity.setActivityName(activityName);
      return  iActivityRepository.save(activity);

    }

    public Boolean deleteActivity(long id) {
        try {
            iActivityRepository.deleteById(id);
            return true;

        }catch (Exception e) {
            return false;
        }


    }
}
