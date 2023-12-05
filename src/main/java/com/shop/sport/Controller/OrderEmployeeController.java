package com.shop.sport.Controller;

import com.shop.sport.DTO.*;
import com.shop.sport.Entity.Order1;
import com.shop.sport.Entity.OrderItem;
import com.shop.sport.Entity.OrderStatus;
import com.shop.sport.Entity.User;
import com.shop.sport.MailService.EmailDetails;
import com.shop.sport.MailService.EmailService;
import com.shop.sport.Response.Response;
import com.shop.sport.Service.OrderService;
import com.shop.sport.Service.OrderStatusService;
import com.shop.sport.Service.UserService;
import com.shop.sport.Utils.PushNoti.FirebaseMessageService;
import com.shop.sport.Utils.PushNoti.Notification;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Annotation
@RestController
@RequestMapping("/api/v1/order-employee")
public class OrderEmployeeController {

    Response response = Response.getInstance();

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderStatusService orderStatusService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;
    @Autowired
    private FirebaseMessageService firebaseMessageService;


    // get chi tiết đơn hàng
    @GetMapping ("/detail/{id}")
    public ResponseEntity<Object> getOrderDetailByID(
            @PathVariable("id") long idOrder
    ) {
        try {

            List<OrderItemDTO> list = orderService.findOrdersItemByID(idOrder);

            return response.generateResponse("get list order item Successfully", HttpStatus.OK, list);

        }catch (Exception e) {
            return response.generateResponse("get list order item failed"+e.getMessage(), HttpStatus.OK, 0 );

        }
    }

    // api nhap hang by employee
    @PostMapping ("/import/{id}")
    public ResponseEntity<Object> NhapHangByEmployee(
            @PathVariable("id") long idUser,
            @RequestBody Map<String, String> body
    ) {
        try {

            int result = orderService.NhapHang(body.get("productIds"), body.get("quantities"),body.get("prices"),
                    idUser);
            if (result==1)
                return response.generateResponse("thêm hóa đơn  Successfully", HttpStatus.OK, result);
            else
                return response.generateResponse("thêm hóa đơn v Successfully", HttpStatus.OK, result);

        }catch (Exception e) {
            return response.generateResponse("thêm hóa đơn  failed"+e.getMessage(), HttpStatus.OK, 0 );

        }
    }

    //get all danh sách hóa đơn theo khoảng thời gian
    @PostMapping ("/all-import-product")
    public ResponseEntity<Object> getAllImport_product(
            @RequestBody Map<String, String> body
    ) {
        try {

            List<DonNhapHang> result = orderService.getAllDonNhapHang(body.get("start"), body.get("end"));

                return response.generateResponse("get all đơn nhập hàng  Successfully", HttpStatus.OK, result);


        }catch (Exception e) {
            return response.generateResponse("get all đơn nhập hàng  failed"+e.getMessage(), HttpStatus.OK, 0 );

        }
    }

    @GetMapping ("/import-product/{id}")
    public ResponseEntity<Object> getImportProductDetailById(
            @PathVariable("id") long id
    ) {
        try {

            List<ImportProductDetail> result = orderService.get_import_product_detail(id);

            return response.generateResponse("get đơn nhập hàng  Successfully", HttpStatus.OK, result);


        }catch (Exception e) {
            return response.generateResponse("get  đơn nhập hàng  failed"+e.getMessage(), HttpStatus.OK, 0 );

        }
    }



    // lấy tất cả các đơn hàng theo ngày đặt từ mới nhất tới cũ nhất
    @GetMapping ("/all")
    public ResponseEntity<Object> getOrderWaitConfirm() {
        try {

            List<Order1> list = orderService.getAllOrder();

            return response.generateResponse("get list order item Successfully", HttpStatus.OK, list);

        }catch (Exception e) {
            return response.generateResponse("get list order item failed"+e.getMessage(), HttpStatus.OK, 0 );

        }
    }
//lấy danh sách đơn hàng theo khoảng thời gian
    @PostMapping ("/all")
    public ResponseEntity<Object> getOrderWaitConfirm(
            @RequestBody Map<String, String> body
    ) {
        try {

            List<Order1> list = orderService.getAllOrderByDate(body.get("date_start"),
                    body.get("date_end"));

            return response.generateResponse("get list order item Successfully", HttpStatus.OK, list);

        }catch (Exception e) {
            return response.generateResponse("get list order item failed"+e.getMessage(), HttpStatus.OK, 0 );

        }
    }

    // lấy tất cả những sản phẩm đã mua nhưng chưa có đánh giá ucar user có id là id
    @GetMapping ("not-evaluate/{id}")
    public ResponseEntity<Object> getOrderNotEvaluate(@PathVariable("id") long iduser) {
        try {

            List<OrderDTO> list = orderService.getOrder_byIdUserNotValuate(iduser);

            return response.generateResponse("get list order chưa được đánh giá  Successfully", HttpStatus.OK, list);

        }catch (Exception e) {
            return response.generateResponse("get list order item failed"+e.getMessage(), HttpStatus.OK, 0 );

        }
    }




    @GetMapping ("/orders")
    public ResponseEntity<Object> getOrdersByEmployee() {
        try {

            List<OrderDTO> list = orderService.getAllOrderByEmployee();
            return response.generateResponse("get list order  Successfully", HttpStatus.OK, list);

        }catch (Exception e) {
            return response.generateResponse("get list order  failed"+e.getMessage(), HttpStatus.OK, 0 );

        }
    }

    @GetMapping ("/best-sell/{thang}/{nam}")
    public ResponseEntity<Object> getBestSell(@PathVariable("thang") int thang,
                                                   @PathVariable("nam")int nam) {
        try {

            List<BestSell> list = orderService.best_sell_month(thang, nam);
            return response.generateResponse("get list order item Successfully", HttpStatus.OK, list);

        }catch (Exception e) {
            return response.generateResponse("get list order item failed"+e.getMessage(), HttpStatus.OK, 0 );

        }
    }

    @GetMapping("/cancel-order/{id}")
    public ResponseEntity<Object> deleteOrder(
            @PathVariable long id
    ) {

        try {


            Order1 order = orderService.findByID(id);
            order.setOrderStatus(orderStatusService.findOrderStatusById(5)); // 5 là id của CANCEL trong bảng order_status
            orderService.saveToDB(order);
            return response.generateResponse("cancel order successfully", HttpStatus.OK, 1);
        } catch (Exception e) {
            return response.generateResponse("cancel order failed"+e.getMessage(), HttpStatus.OK, null);

        }
    }

    @PostMapping("/update-status-order/{id}")
    public ResponseEntity<Object> UpdateStatusOrder(
            @PathVariable("id") long idOrder,
            @RequestBody Map<String, String> body

    ) {
        try {
            long idStatusOrder = Long.parseLong( body.get("id_order_status"));
            Order1 order = orderService.findByID(idOrder);

            //đơn hàng chờ xác nhận mới có thể hủy
            if (order.getOrderStatus().getId()==1 && idStatusOrder==5) {
                OrderStatus orderStatus = orderStatusService.findOrderStatusById(idStatusOrder);
                order.setOrderStatus(orderStatus); // 5 là id của CANCEL trong bảng order_status
                orderService.saveToDB(order);
                return response.generateResponse("Hủy đơn hàng thành công", HttpStatus.OK, order);
            }

            if ((idStatusOrder==4||idStatusOrder==3||idStatusOrder==2|| idStatusOrder==1) &&order.getOrderStatus().getId()==5){
                return response.generateResponse("Chuyển trạng thái sai !!!", HttpStatus.OK, 0);
            } else if (order.getOrderStatus().getId()==2 && idStatusOrder==1)
                return response.generateResponse("Chuyển trạng thái sai !!!", HttpStatus.OK, 0);
            else if (order.getOrderStatus().getId()==3 &&( idStatusOrder==2|| idStatusOrder==1)) {
                return response.generateResponse("Chuyển trạng thái sai !!!", HttpStatus.OK, 0);
            }else if (order.getOrderStatus().getId()==4 && (idStatusOrder==3||idStatusOrder==2|| idStatusOrder==1)){
                return response.generateResponse("Chuyển trạng thái sai !!!", HttpStatus.OK, 0);
            }

            // nếu bil; =0 thì ko xuất hóa đơn bằng 1thifif xuất hóa đơn
            if(order.getOrderStatus().getId()==1 && idStatusOrder==2) { //xuất bill
                LocalDate currentDate = LocalDate.now();
                // Định dạng thời gian theo "yyyy-MM-dd"
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String formattedDate = currentDate.format(formatter);
                order.setOrderDate(Date.valueOf(formattedDate));
                OrderStatus orderStatus = orderStatusService.findOrderStatusById(idStatusOrder);
                order.setOrderStatus(orderStatus);
                orderService.saveToDB(order);

                try {
                    EmailDetails details = new EmailDetails();
                    details.setSubject("ĐẶT MUA HÀNG THÀNH CÔNG !!!");
                    details.setMsgBody("Bạn đã đặt mua sản phẩm :" +
                            "\n\nĐơn hàng sẽ sớm được gửi cho bạn, cảm ơn quý khách hàng đã tin tưởng và ủng hộ Shop");
                    details.setRecipient(body.get("email"));
                    details.setAttachment("\"C:\\Users\\nhona\\OneDrive\\Máy tính\\Nam\\image\\empty-cart.jpg\"");
                    Boolean status
                            = emailService.sendMailWithAttachment(details);;


                } catch (Exception e) {
                }

                //push noti
                try {
                    User user = userService.getUserById(Long.parseLong(body.get("id_user")));
                    if(user.getTokenDevice()==null ||user.getTokenDevice()==""){
                        return response.generateResponse("1", HttpStatus.OK, order);

                    }

                    Notification note = new Notification();
                    note.setContent(body.get("content"));
                    note.setSubject(body.get("title"));
                    if(body.get("image_url")==null || body.get("image_url")=="")
                        note.setImage("https://res.cloudinary.com/dzljztsyy/image/upload/v1700793742/shop_sport/avatart%20default/logoshop_gtr9tk.png");
                    else
                        note.setImage(body.get("image_url"));

                    firebaseMessageService.sendNotification(note, user.getTokenDevice());


                } catch (Exception e) {
                    return response.generateResponse("1", HttpStatus.OK, order);                }

                return response.generateResponse("1", HttpStatus.OK, order);

            }
            OrderStatus orderStatus = orderStatusService.findOrderStatusById(idStatusOrder);
            order.setOrderStatus(orderStatus);

            orderService.saveToDB(order);
            return response.generateResponse("chuyển trạng thái thành công", HttpStatus.OK, order);

        } catch (Exception e) {
            return response.generateResponse("update status order"+e.getMessage(), HttpStatus.OK, 0);
        }
    }


    @GetMapping ("/data-chart/{nam}")
    public ResponseEntity<Object> getDataCHart(
                                              @PathVariable("nam")int nam) {
        try {

            String list = orderService.get_data_chart( nam);

            return response.generateResponse("get list order item Successfully", HttpStatus.OK, list);

        }catch (Exception e) {
            return response.generateResponse("get list order item failed"+e.getMessage(), HttpStatus.OK, 0 );

        }
    }

    @GetMapping ("/export-bill/{id}")
    public ResponseEntity<Object> XuatHoaDon(@PathVariable("id") long idOder) {
        try {

            List<HoaDon> hoaDons = orderService.HoaDon(idOder);

            return response.generateResponse("get list order item Successfully", HttpStatus.OK, hoaDons);

        }catch (Exception e) {
            return response.generateResponse("get list order item failed"+e.getMessage(), HttpStatus.BAD_REQUEST, null);

        }
    }


//    @GetMapping ("/wait-confirm")
//    public ResponseEntity<Object> DonHangChoXacNhan() {
//        try {
//
//            List<Order1> list = orderService.listOrderWaitConfirm();
//
//            return response.generateResponse("get list order wait confirm", HttpStatus.OK, list);
//
//        }catch (Exception e) {
//            return response.generateResponse("get list order wait confirm failed"+e.getMessage(), HttpStatus.BAD_REQUEST, null);
//
//        }
//    }

//    @GetMapping ("/done-confirm")
//    public ResponseEntity<Object> DonHangDaXacNhan() {
//        try {
//
//            List<Order1> list = orderService.listOrderConfirmed();
//
//            return response.generateResponse("get list order wait confirm", HttpStatus.OK, list);
//
//        }catch (Exception e) {
//            return response.generateResponse("get list order wait confirm failed"+e.getMessage(), HttpStatus.BAD_REQUEST, null);
//
//        }
//    }


    @PostMapping("/confirm")
    public ResponseEntity<Object> confirmByEmployee(@RequestBody Map<String, String> body) {


        try {
            long idSeler = Long.parseLong(body.get("id_seller"));

            long idOder = Long.parseLong(body.get("id_order"));
            long idPaymentMethod = Long.parseLong(body.get("idPaymentMethod"));
            float paymentAmount = orderService.total_order(idOder);
            Boolean confirmOrder= orderService.confirmOrder(idSeler,
                    paymentAmount,idOder ,idPaymentMethod);


           Order1 order1 = orderService.findByID(idOder);
           User Customer = userService.getUserById(order1.getUser().getId());

            try {
                EmailDetails details = new EmailDetails();
                details.setSubject("ĐẶT MUA HÀNG THÀNH CÔNG !!!");
                details.setMsgBody("Bạn đã đặt mua sản phẩm :" +
                        "\n\nĐơn hàng sẽ sớm được gửi cho bạn, cảm ơn quý khách hàng đã tin tưởng và ủng hộ Shop");
                details.setRecipient(Customer.getEmail());
                Boolean status
                        = emailService.sendSimpleMail(details);;

                return response.generateResponse("confirmOrder product Successfully !", HttpStatus.OK, status);
            } catch (Exception e) {
                return response.generateResponse(" confirmOrder sent mail failed Provide accout failed" + e.getMessage(), HttpStatus.BAD_REQUEST, true);
            }


//            return response.generateResponse("confirmOrder product Successfully", HttpStatus.OK, true);

        }catch (Exception e) {
            return response.generateResponse("confirmOrder product fail"+e.getMessage(), HttpStatus.BAD_REQUEST, null);

        }
    }

}
