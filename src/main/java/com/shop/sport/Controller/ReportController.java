package com.shop.sport.Controller;

import com.shop.sport.DTO.Report;
import com.shop.sport.DTO.ReportDTO;
import com.shop.sport.DTO.SellestDTO;
import com.shop.sport.Entity.Unit;
import com.shop.sport.Response.Response;
import com.shop.sport.Service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
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

    @PostMapping("/best-sell")
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
    public  String FormatPrice( Double price){
        DecimalFormat formatter = new DecimalFormat("#,###");
        String formattedValue = formatter.format( Double.valueOf(String.format("%.0f", price)));
        return formattedValue + "  VND";
    }

    @PostMapping("/report-excel")
    public ResponseEntity<Object> Report(
            @RequestBody Map<String, String> body
    ) {
        try {

            List<Report> list = unitService.ThongTinBanHangTheoKhoangThoiGian( body.get("start"), body.get("end"));
            List<ReportDTO> result = new ArrayList<>();
            ReportDTO reportDTO;
            for (int i = 0; i < list.size(); i++) {

                 reportDTO = new ReportDTO();
                 reportDTO.setTongDoanhThu( FormatPrice(Double.valueOf(list.get(i).getTongDoanhThu())) );
                 reportDTO.setTongLoiNhuan( FormatPrice(Double.valueOf(list.get(i).getTongLoiNhuan())) );
                 reportDTO.setTongChiPhi( FormatPrice(Double.valueOf(list.get(i).getTongChiPhi())) );
                 reportDTO.setNgay( list.get(i).getNgay() );
                 reportDTO.setTenSanPham( list.get(i).getTenSanPham() );
                 reportDTO.setTongSoLuongBan( list.get(i).getTongSoLuongBan() );
                 result.add(reportDTO);


            }

            return response.generateResponse("create units succesfully", HttpStatus.OK, result);
        } catch (Exception e) {
            return response.generateResponse("create units fail" + e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }

    }

}
