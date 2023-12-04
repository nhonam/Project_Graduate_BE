package com.shop.sport.Service;

import com.shop.sport.Entity.Banner;
import com.shop.sport.Repositories.IBannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BannerService {
    @Autowired
    private IBannerRepository iBannerRepository;


    public void delete(long id) {
        iBannerRepository.deleteById(id);
    }

    public List<Banner> getAllBanner() {
        return (List<Banner>) iBannerRepository.findAll();
    }


    public List<Banner> getFiveBanner() {
        List<Banner> banners = (List<Banner>) iBannerRepository.findAll();

        List<Banner> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            list.add(banners.get(i));
        }

        return list;
    }

    public Optional<Banner> findById(long id) {
        return iBannerRepository.findById(id);
    }


    public Banner createBanner(Banner category) {

        return iBannerRepository.save(category);
    }
}
