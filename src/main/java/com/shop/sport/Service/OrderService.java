package com.shop.sport.Service;

import com.shop.sport.DTO.*;
import com.shop.sport.Entity.Order1;
import com.shop.sport.Entity.OrderItem;
import com.shop.sport.Repositories.IOrderItem;
import com.shop.sport.Repositories.OrderReopository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderReopository orderReopository;

    @Autowired
    private IOrderItem orderItemRepo;


    public OrderItem findOrderItemByID(long id) {
        return  orderItemRepo.findById(id).orElse(null);
    }

    public List<OrderItemDTO> findOrdersItemByID(long id_order) {
        return  orderItemRepo.get_all_evaluate_by_id_product(id_order);
    }

    public List<DonNhapHang> getAllDonNhapHang(String start, String end) {
        return  orderItemRepo.getall_don_nhap_hang(start, end);
    }

    public int NhapHang(String productIds, String quantities, String prices, long id_employee) {
        return  orderItemRepo.nhap_hang(productIds,quantities, prices, id_employee);
    }

    public List<ImportProductDetail> get_import_product_detail(long id) {
        return  orderItemRepo.get_import_product_detail(id);
    }



    public int  insertOrder(long idUser, String adress, String phone, String name_reciver,
                            String productIds, String quantities ) {
            try {
               return orderReopository.insert_to_order_order_item(adress,idUser,phone,name_reciver, productIds, quantities);

            }catch (Exception e) {
                return 0;
            }

    }

    public Order1 saveToDB(Order1 order1){
        return orderReopository.save(order1);
    }

    public Order1 findByID(long id) {
        return  orderReopository.findById(id).orElse(null);
    }

    public List<OrderDTO> getOrder_byIdUser(long idUser) {
            return orderReopository.getOrder_byIdUser(idUser);

    }
    public List<Order1> getAllOrder() {
        return (List<Order1>) orderReopository.findAll();

    }

    public List<Order1> getAllOrderByDate(String start, String end) {
        return (List<Order1>) orderReopository.get_order_by_date(start, end);

    }
    public List<OrderDTO> getAllOrderByEmployee() {
        return orderReopository.get_all_order_by_employee();

    }

    public List<OrderDTO> getOrder_byIdUserNotValuate(long idUser) {
        return orderReopository.getOrder_byIdUser_evaluate(idUser);

    }

    public List<BestSell> best_sell_month(int thang, int nam) {
        return orderReopository.best_sell_month(thang, nam);

    }

    public Long deleteOder(long id) {
        Long result = orderReopository.delete_order(id);
        return result;
    }

    public String get_data_chart( int nam) {
        return orderReopository.get_data_chart( nam);

    }

    public long getSumSell() {
        List<OrderItem> list = (List<OrderItem>) orderItemRepo.findAll();
        long sum=0;
        for (int i = 0; i < list.size(); i++) {
            sum+=1;
        }
        return sum;

    }

    public List<HoaDon> HoaDon(long idOrder) {
        return orderReopository.hoadon(idOrder);

    }

//    public List<Order1> listOrderWaitConfirm() {
//        return orderReopository.findOrdersWithoutPayment();
//
//    }
//
//    public List<Order1> listOrderConfirmed() {
//        return orderReopository.findOrdersPayment();
//
//    }

    public Long total_order(long idOrder) {
        return orderReopository.total_order(idOrder);

    }

    public Boolean confirmOrder(long idSeller, float paymentAmount, long idOder, long idPaymentMethod) {
        try {
            orderReopository.comfirm_order(idSeller,paymentAmount,idOder,idPaymentMethod);

            return true;
        }catch (Exception e){
            return false;
        }
    }
}
