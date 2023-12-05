package com.shop.sport.Repositories;

import com.shop.sport.DTO.BestSell;
import com.shop.sport.DTO.HoaDon;
import com.shop.sport.DTO.OrderDTO;
import com.shop.sport.Entity.Order1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
@EnableJpaRepositories
public interface OrderReopository extends CrudRepository<Order1, Long> {

//    @Query(value = "CALL insert_to_order_order_item(:shipping_adress,:id_user,:phone,:ngnhan,:productIds,:quantities);", nativeQuery = true)
//    int insert_to_order_order_item(@Param("shipping_adress") String shipping_adress,
//                         @Param("id_user") long id_user,
//                         @Param("phone") String phone,
//                         @Param("name_reciver") String name_reciver,
//                         @Param("productIds") String productIds,
//                         @Param("quantities") String quantities);
//    @Procedure(name = "getOrder_byIdUser")
//    List<OrderDTO> getOrder_byIdUser(@Param("id_user") long id_user);
@Query(value = "CALL  (:shipping_adress,:id_user,:phone,:ngnhan,:productIds,:quantities);", nativeQuery = true)
int insert_to_order_order_item(@Param("shipping_adress") String shipping_adress,
                         @Param("id_user") long id_user,
                         @Param("phone") String phone,
                         @Param("ngnhan") String ngnhan,
                         @Param("productIds") String productIds,
                         @Param("quantities") String quantities);
    @Query(value = "CALL getOrder_byIdUser_by_date(:id_User,:start,:end)", nativeQuery = true)
    List<OrderDTO> getOrder_byIdUser_by_date(@Param("id_User") long id_User,
                                             @Param("start") String start,
                                             @Param("end") String end);

    @Query(value = "CALL get_all_order_by_employee()", nativeQuery = true)
    List<OrderDTO> get_all_order_by_employee();

    @Query(value = "CALL best_sell_month(:thang, :nam)", nativeQuery = true)
    List<BestSell> best_sell_month(@Param("thang") int thang,
                                   @Param("nam") int nam);

    @Query(value = "CALL getOrder_byIdUser_evaluate(:id_User)", nativeQuery = true)
    List<OrderDTO> getOrder_byIdUser_evaluate(@Param("id_User") long id_User);
    @Query(value = "CALL get_order_by_date(:start,:end)", nativeQuery = true)
    List<Order1> get_order_by_date(@Param("start") String start,
                                     @Param("end") String end);

    @Query(value = "CALL delete_order(:idorder);", nativeQuery = true)
    Long delete_order(@Param("idorder") long idorder);

    @Query(value = "CALL get_data_chart( :nam)", nativeQuery = true)
    String get_data_chart(@Param("nam") int nam);


    @Query(value = "CALL hoadon(:idOder)", nativeQuery = true)
    List<HoaDon> hoadon(@Param("idOder") long idOder);


    @Query(value = "CALL total_order(:idOrder)", nativeQuery = true)
    Long total_order(@Param("idOrder") long idOrder);

    @Procedure(name = "comfirm_order")
    void comfirm_order(@Param("idEmployee") long idSeller,
                       @Param("payment_amout") float paymentAmount,
                       @Param("id_order") long idOder,
                       @Param("id_payment_method") long idPaymentMothod);

//    @Query("SELECT o FROM Order1 o LEFT JOIN OrderPayment op ON o.id = op.order.id WHERE op.order IS NULL")
//    List<Order1> findOrdersWithoutPayment();
//
//    @Query("SELECT o FROM Order1 o JOIN OrderPayment op ON o.id = op.order.id")
//    List<Order1> findOrdersPayment();


}
