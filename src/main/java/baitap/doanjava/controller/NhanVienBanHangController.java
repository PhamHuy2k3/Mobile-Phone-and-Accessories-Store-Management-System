package baitap.doanjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import baitap.doanjava.service.NhanVienService;

@Controller
@RequestMapping("/nhanvienbanhang")
public class NhanVienBanHangController {
    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping("/luong")
    public String hienThiLuong(Model model) {
        double luongCoBan = 5000000;
        var dsBanHang = nhanVienService.getAllBanHang();
        model.addAttribute("dsBanHang", dsBanHang);
        model.addAttribute("luongCoBan", luongCoBan);
        return "nhanvien/luongbanhang";
    }
}
