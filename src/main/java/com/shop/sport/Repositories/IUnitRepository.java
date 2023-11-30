package com.shop.sport.Repositories;

import com.shop.sport.DTO.Report;
import com.shop.sport.DTO.SellestDTO;
import com.shop.sport.Entity.Activity;
import com.shop.sport.Entity.Unit;
import com.shop.sport.Entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUnitRepository extends CrudRepository<Unit, Long> {

    @Query(value = "CALL is_exsit_unit(:unit_name);", nativeQuery = true)
    Long isExsitUnit(@Param("unit_name") String unit_name);


    @Query(value = "CALL ThongKeSoLuongSanPhamBanRa(:year);", nativeQuery = true)
    String ThongKeSoLuongSanPhamBanRa(@Param("year") int year);

    @Query(value = "CALL top_sanpham_banchay(:month,:year);", nativeQuery = true)
    List<SellestDTO> top_sanpham_banchay(@Param("month") int month, @Param("year") int year);

    @Query(value = "CALL ThongTinBanHangTheoKhoangThoiGian(:month,:year);", nativeQuery = true)
    List<Report> ThongTinBanHangTheoKhoangThoiGian(@Param("month") String month, @Param("year") String year);


}
