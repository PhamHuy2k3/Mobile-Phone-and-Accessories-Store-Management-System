package baitap.doanjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import baitap.doanjava.service.ThongKeService;

@Controller
@RequestMapping("/thongke")
public class ThongKeController {
    @Autowired
    private ThongKeService thongKeService;

    @GetMapping
    public String thongKeTongQuan(Model model) {
        return "thongke/index";
    }

    @GetMapping("/doanhthu")
    public String thongKeDoanhThu(@RequestParam(value = "year", required = false) Integer year, Model model) {
        int currentYear = java.time.LocalDate.now().getYear();
        int selectedYear = (year != null) ? year : currentYear;
        model.addAttribute("selectedYear", selectedYear);
        model.addAttribute("doanhThuThang", thongKeService.thongKeDoanhThuTheoThang(selectedYear));
        model.addAttribute("doanhThuQuy", thongKeService.thongKeDoanhThuTheoQuy(selectedYear));
        model.addAttribute("doanhThuNam", thongKeService.thongKeDoanhThuTheoNam());
        return "thongke/doanhthu";
    }

    @GetMapping("/kpi")
    public String thongKeKPI(@RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "month", required = false) Integer month,
            Model model) {
        int currentYear = java.time.LocalDate.now().getYear();
        int currentMonth = java.time.LocalDate.now().getMonthValue();
        int selectedYear = (year != null) ? year : currentYear;
        int selectedMonth = (month != null) ? month : currentMonth;
        model.addAttribute("selectedYear", selectedYear);
        model.addAttribute("selectedMonth", selectedMonth);
        model.addAttribute("kpiList", thongKeService.thongKeKPINhanVien(selectedYear, selectedMonth));
        return "thongke/kpi";
    }
}
