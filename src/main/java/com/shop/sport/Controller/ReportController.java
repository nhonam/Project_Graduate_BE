package com.shop.sport.Controller;

import com.shop.sport.DTO.Report;
import com.shop.sport.DTO.SellestDTO;
import com.shop.sport.Entity.Unit;
import com.shop.sport.Response.Response;
import com.shop.sport.Service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/report-admin")
public class ReportController {

    Response response = Response.getInstance();

    @Autowired
    private UnitService unitService;



    @GetMapping("/month/{id}")
    public ResponseEntity<Object> createActivity(
            @PathVariable("id") int year
    ) {
        try {

            String list = unitService.listReportMonth(year);
            List<Integer> integerList = Arrays.stream(list.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            return response.generateResponse("create units succesfully", HttpStatus.OK, integerList);
        } catch (Exception e) {
            return response.generateResponse("create units fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }

    @GetMapping("/best-sell")
    public ResponseEntity<Object> createActivity(
            @RequestBody Map<String, String> body
    ) {
        try {

            List<SellestDTO> list = unitService.top_sanpham_banchay( Integer.parseInt(body.get("month")),Integer.parseInt( body.get("year")));

            return response.generateResponse("create units succesfully", HttpStatus.OK, list);
        } catch (Exception e) {
            return response.generateResponse("create units fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }

    @PostMapping("/report-excel")
    public ResponseEntity<Object> Report(
            @RequestBody Map<String, String> body
    ) {
        try {

            List<Report> list = unitService.ThongTinBanHangTheoKhoangThoiGian( body.get("start"), body.get("end"));

            return response.generateResponse("create units succesfully", HttpStatus.OK, list);
        } catch (Exception e) {
            return response.generateResponse("create units fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }

}
