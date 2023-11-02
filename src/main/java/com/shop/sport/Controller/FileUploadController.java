//package com.shop.sport.Controller;
//
//
//import com.shop.sport.Service.FileUpload;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//@Controller
//@RequiredArgsConstructor
//public class FileUploadController {
//
//
//    @Autowired
//    private FileUpload fileUpload;
//
//    @RequestMapping("/")
//    public String home(){
//        return "home";
//    }
//
//    @PostMapping("/upload")
//    public String uploadFile(@RequestParam("image")MultipartFile multipartFile,
//                             Model model) throws IOException {
//        String imageURL = fileUpload.uploadFile(multipartFile);
//        model.addAttribute("imageURL",imageURL);
//        return "gallery";
//    }
//}
