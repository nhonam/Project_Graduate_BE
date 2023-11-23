package com.shop.sport.Controller;

import com.shop.sport.Entity.Role;
import com.shop.sport.Entity.User;
import com.shop.sport.MailService.EmailDetails;
import com.shop.sport.MailService.EmailService;
import com.shop.sport.Response.Response;
import com.shop.sport.Service.FileUpload;
import com.shop.sport.Service.RoleService;
import com.shop.sport.Service.UserService;
import com.shop.sport.Utils.PushNoti.FirebaseMessageService;
import com.shop.sport.Utils.PushNoti.Notification;
import com.shop.sport.Utils.Utils;
import com.shop.sport.auth.AuthenticationService;
import com.shop.sport.auth.RegisterRequest;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    Response response = Response.getInstance();
    @Autowired
    private FileUpload fileUpload;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private  AuthenticationService authenticationService;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private FirebaseMessageService firebaseMessageService;


    @PostMapping("/push-noti/{id}")
    public ResponseEntity<Object> pushnotification(
            @PathVariable long id,
            @RequestBody Map<String, String> body
    ) {
        try {

            Notification note = new Notification();
            note.setContent(body.get("content"));
            note.setSubject(body.get("title"));
            note.setImage("https://res.cloudinary.com/dzljztsyy/image/upload/v1700707731/shop_sport/avatart%20default/3531a97a-613d-47f5-8e6a-5fa6f780a2fa_q1jgan.png");



            User user = userService.getUserById(id);
            System.out.println("------------");
            System.out.println(user.getTokenDevice());
            firebaseMessageService.sendNotification(note, user.getTokenDevice());


            return response.generateResponse("push notification Successfully", HttpStatus.OK, "done");
        } catch (Exception e) {
            return response.generateResponse("push notification fail"+e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }


    }



    @PostMapping("/find-device/{id}")
    public ResponseEntity<Object> finDeviceTokenAndSave(
            @PathVariable long id,
            @RequestBody Map<String, String> body

    ) {

        try {
            User users = userService.getUserById(id);
            if (users.getTokenDevice()==null || users.getTokenDevice()==""|| users.getTokenDevice()!=body.get("fcm_token")){
                users.setTokenDevice(body.get("fcm_token"));
                userService.updateUser(users);

                return response.generateResponse("Save device token successfully", HttpStatus.OK, users);
            }

            List<User> list = userService.getAllUsers();

            for (int i = 0; i < list.size(); i++) {

                if (users.getTokenDevice()==list.get(i).getTokenDevice()){
                    return response.generateResponse("Device token is exsit", HttpStatus.BAD_REQUEST, users);
                }
                users.setTokenDevice(body.get("fcm_token"));
                userService.updateUser(users);

                return response.generateResponse("Save device token successfully", HttpStatus.OK, users);

            }
            return response.generateResponse("Save device token successfully", HttpStatus.OK, users);

        } catch (Exception e) {
            return response.generateResponse("Save Device token fail"+e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }


    }


    @GetMapping("/getAllEmployee")
    public ResponseEntity<Object> getAllEmployeeByAdmin() {

        try {
            List<User> users = userService.getAllUserByRole("EMPLOYEE");

            return response.generateResponse("Get All employee Successfully", HttpStatus.OK, users);
        } catch (Exception e) {
            return response.generateResponse("Get All employee fail"+e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }


    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Object> findUserbyId(
            @PathVariable long id
    ) {

        try {
            User users = userService.getUserById(id);

            return response.generateResponse("Get All employee Successfully", HttpStatus.OK, users);
        } catch (Exception e) {
            return response.generateResponse("Get All employee fail"+e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }


    }


    @PostMapping("/search/employee")
    public ResponseEntity<Object> searchEmployee(   @RequestBody Map<String, String> body) {

        try {
            List<User> users = userService.searchEmployee(body.get("query"));

            return response.generateResponse("searchEmployee Successfully", HttpStatus.OK, users);
        } catch (Exception e) {
            return response.generateResponse("searchEmployee fail"+e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }


    }

    @PostMapping("/search/customer")
    public ResponseEntity<Object> searchCustomer(   @RequestBody Map<String, String> body) {

        try {
            List<User> users = userService.searchCustomer(body.get("query"));

            return response.generateResponse("searchCustomer Successfully", HttpStatus.OK, users);
        } catch (Exception e) {
            return response.generateResponse("searchCustomer fail"+e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }


    }

    @GetMapping("/getAllCustomer")
    public ResponseEntity<Object> getAllCustomerByAdmin() {

        try {
//            List<User> users = userService.getAllUsers();
            List<User> users = userService.getAllUserByRole("CUSTOMER");

            return response.generateResponse("Get All CUSTOMER Successfully", HttpStatus.OK, users);
        } catch (Exception e) {
            return response.generateResponse("Get All CUSTOMER fail in UserController.java" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Object> deleteAcount(
            @PathVariable long id
    ) {
        try {
            if(userService.checkDeleteUser(id) == 0) {
                return response.generateResponse("Tài khoản đã không thể xóa !", HttpStatus.OK, null);

            }
            User users = userService.updateStatusUser(id);
            return response.generateResponse("remove user Successfully", HttpStatus.OK, users);
        } catch (Exception e) {
            return response.generateResponse("remove user fail in UserController.java" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Object> updateUserByAdmin(
            @RequestBody Map<String, String> body,
           @PathVariable long id
    ) {
        try {
            User updateUser = userService.getUserById(id);
            String fullname = body.get("fullname");
            String email = body.get("email");
            String sdt = body.get("sdt");

            System.out.println(email);
            if ( fullname != null) {
                updateUser.setFullname(fullname);
            }
            if (email != null) {
                updateUser.setEmail(email);
            }
            if (sdt != null) {
                updateUser.setPhone(sdt);
            }
            userService.updateUser(updateUser);


            return response.generateResponse("Cập nhật tài khoản thành công", HttpStatus.OK, updateUser);
        } catch (Exception e) {
            return response.generateResponse("Cập nhật tài khoản fail in UserController.java" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }


    @PostMapping("/change-password/{id}")
    public ResponseEntity<Object> changePassword(
            @RequestBody Map<String, String> body,
            @PathVariable long id
    ) {
        try {

            User updateUser = userService.getUserById(id);
            String password = body.get("newpassword");
            updateUser.setPassword(passwordEncoder.encode(password));
            userService.updateUser(updateUser);


            return response.generateResponse("Cập nhật tài khoản thành công", HttpStatus.OK, updateUser);
        } catch (Exception e) {
            return response.generateResponse("Cập nhật tài khoản fail in UserController.java" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }




    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> updateProfile(@PathVariable long id,
                                                @RequestParam(value = "email", required = false) String email,
                                                @RequestParam(value = "fullname", required = false) String fullname,
                                                @RequestParam(value = "phone", required = false) String phone,
                                                @RequestParam(value = "adress", required = false) String adress,
                                                @RequestParam(value = "birthday", required = false) String birthday,
                                                @RequestParam(value = "image" , required = false) MultipartFile multipartFile

    ) {

        try {
//            System.out.println(multipartFile+"------------");
            User updateUser = userService.getUserById(id);
            if(updateUser==null) {
                return response.generateResponse("user not found", HttpStatus.BAD_REQUEST, null);
            }else {
                if (fullname != null) {
                    updateUser.setFullname(fullname);
                }
                if (phone != null) {
                    updateUser.setPhone(phone);
                }

                if (adress != null) {
                    updateUser.setAdress(adress);
                }
                if (email != null) {
                    updateUser.setEmail(email);
                }

                if (birthday != null) {

                    updateUser.setBirthday(Utils.convertStringToDate(birthday));
                }
                if (multipartFile!=null) {

                    if (updateUser.getPublicId() != null) {
                        fileUpload.deleteFile(updateUser.getPublicId());
                    }
                    Map<String, String> upload = fileUpload.uploadFile(multipartFile);

                    updateUser.setAvatarUrl(upload.get("url"));
                    updateUser.setPublicId(upload.get("public_id"));

                }

                updateUser.setStatus(true);
                userService.updateUser(updateUser);
                return response.generateResponse("update profile successfully", HttpStatus.OK, updateUser);

            }
        } catch (Exception e) {
            // TODO: handle exception
            return response.generateResponse("update profile failed"+e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }


    @PostMapping("/createAccEmployee")
    public ResponseEntity<Object> createAdmin(
            @RequestBody Map<String, String> body
    ) {

        try {

//            if(userService.checkUserExist(body.get("email")) ){
//                return response.generateResponse("tài khaorn đã tồn tại" , HttpStatus.BAD_REQUEST, true);
//
//            }

            RegisterRequest registerRequest = new RegisterRequest();
            registerRequest.setEmail(body.get("email"));
            registerRequest.setRole(roleService.getRoleById(2));
            registerRequest.setUsername(body.get("email"));
            String randomPassWord = Utils.generateRandomString(6);
            registerRequest.setPassword(randomPassWord);


            if (authenticationService.checkUserExist(registerRequest.getUsername()))
                return response.generateResponse("User exist!", HttpStatus.BAD_REQUEST, null);


            //sentmail
            try {
                EmailDetails details = new EmailDetails();
                details.setSubject("THÔNG TIN TÀI KHOẢN NHÂN VIÊN");
                details.setMsgBody("Chúc Mừng Bạn Đã Trở Thành Nhân Viên Của Cửa Hàng Kinh Doanh Dụng Cụ Thể Thao Nho Nam" +
                        "\n\nTài Khoản Được Cấp Của Bạn Là :"+"\n\nUserName:"+registerRequest.getUsername()+"\nMật Khẩu:"+ randomPassWord + "\n\nVui lòng đăng nhập và đổi mật khẩu");
                details.setRecipient(registerRequest.getEmail());
                Boolean status
                        = emailService.sendSimpleMail(details);;
                User users = authenticationService.register(registerRequest);
                return response.generateResponse("Provide accout Successfully !", HttpStatus.OK, users);
            } catch (Exception e) {
                return response.generateResponse("sent mail failed Provide accout failed" + e.getMessage(), HttpStatus.BAD_REQUEST, true);
            }


        } catch (Exception e) {
            return response.generateResponse("Provide accout failed" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @PostMapping("/forget-password/{id}")
    public ResponseEntity<Object> forgetPass(
            @RequestBody Map<String, String> body,
            @PathVariable long id
    ) {
        try {
          String email = body.get("email");
            User updateUser = userService.getUserById(id);
            String password = Utils.generateRandomString(6);
            updateUser.setPassword(passwordEncoder.encode(password));
            userService.updateUser(updateUser);
            //sentmail
            try {
                EmailDetails details = new EmailDetails();
                details.setSubject("Cấp Lại Mật Khẩu !!!");
                details.setMsgBody("Bạn đã yêu cầu cấp lại mật khẩu mới" +
                        "\n\nĐây là mật khẩu mới của bạn :"+ password +"\n\nVui lòng đăng nhập và đổi mật khẩu");
                details.setRecipient(email);
                Boolean status
                        = emailService.sendSimpleMail(details);;

                return response.generateResponse("Provide accout Successfully !", HttpStatus.OK, status);
            } catch (Exception e) {
                return response.generateResponse("sent mail failed Provide accout failed" + e.getMessage(), HttpStatus.BAD_REQUEST, true);
            }


        } catch (Exception e) {
            return response.generateResponse("Provide accout failed" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

}
