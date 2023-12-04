package com.shop.sport.Repositories;

import com.shop.sport.DTO.DonNhapHang;
import com.shop.sport.DTO.ImportProductDetail;
import com.shop.sport.DTO.OrderItemDTO;
import com.shop.sport.Entity.OrderItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderItem extends CrudRepository<OrderItem, Long> {

    @Query(value = "CALL get_order_item_by_id_order(:od_order)", nativeQuery = true)
    List<OrderItemDTO> get_all_evaluate_by_id_product(@Param("od_order") long od_order);

    @Query(value = "CALL getall_don_nhap_hang(:start, :end)", nativeQuery = true)
    List<DonNhapHang> getall_don_nhap_hang(@Param("start") String start,
                                           @Param("end")String end);

    @Query(value = "CALL get_import_product_detail(:id)", nativeQuery = true)
    List<ImportProductDetail> get_import_product_detail(@Param("id") long id);

    @Query(value = "CALL nhap_hang(:productIds,:quantities,:prices,:employee_id)", nativeQuery = true)
    int nhap_hang(
            @Param("productIds") String productIds,
            @Param("quantities") String quantities,
            @Param("prices") String prices,
            @Param("employee_id") long employee_id
    );

}
