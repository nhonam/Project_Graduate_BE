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
    public Boolean check_delete_banner(long id) {
        return iBannerRepository.check_delete_banner() == 1;
    }

    public Boolean delete_Banner(long id) {
        try {
            iBannerRepository.deleteById(id);
            return true;

        }catch (Exception e) {
            return false;
        }


    }
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
