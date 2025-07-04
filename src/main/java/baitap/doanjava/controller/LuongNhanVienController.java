package baitap.doanjava.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import baitap.doanjava.entity.LuongNhanVien;
import baitap.doanjava.service.LuongNhanVienService;
import baitap.doanjava.service.NhanVienService;

@Controller
@RequestMapping("/luongnhanvien")
public class LuongNhanVienController {
    @Autowired
    private LuongNhanVienService luongNhanVienService;

    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping("")
    public String index(Model model,
            @RequestParam(value = "thang", required = false) String thang,
            @RequestParam(value = "vaiTro", required = false) String vaiTro,
            @RequestParam(value = "tuKhoa", required = false) String tuKhoa) {
        model.addAttribute("dsLuong", luongNhanVienService.filterLuongNhanVien(thang, vaiTro, tuKhoa));
        // Lấy danh sách tháng có trong bảng lương
        List<String> dsThang = luongNhanVienService.getAllBangLuong().stream().map(LuongNhanVien::getThang).distinct()
                .collect(Collectors.toList());
        model.addAttribute("dsThang", dsThang);
        // Lấy danh sách vai trò từ bảng nhân viên
        List<String> dsVaiTro = nhanVienService.getAll().stream().map(nv -> nv.getVaiTro()).distinct()
                .collect(Collectors.toList());
        model.addAttribute("dsVaiTro", dsVaiTro);
        return "luongnhanvien/index";
    }

    @GetMapping("/them")
    public String them(Model model) {
        model.addAttribute("luong", new LuongNhanVien());
        return "luongnhanvien/them";
    }

    @PostMapping("/luu")
    public String luu(LuongNhanVien luong) {
        // KHÔNG set tổng lương, chỉ lưu lương cơ bản, phụ cấp, khấu trừ
        luongNhanVienService.save(luong);
        return "redirect:/luongnhanvien";
    }

    @GetMapping("/sua/{maLuong}")
    public String sua(@PathVariable("maLuong") String maLuong, Model model) {
        LuongNhanVien luong = luongNhanVienService.getById(maLuong);
        if (luong != null) {
            model.addAttribute("luong", luong);
            return "luongnhanvien/them";
        }
        return "redirect:/luongnhanvien";
    }

    @GetMapping("/xoa/{maLuong}")
    public String xoa(@PathVariable("maLuong") String maLuong) {
        luongNhanVienService.delete(maLuong);
        return "redirect:/luongnhanvien";
    }
}
