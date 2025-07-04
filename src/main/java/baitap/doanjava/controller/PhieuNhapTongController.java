package baitap.doanjava.controller;

import baitap.doanjava.entity.PhieuNhap;
import baitap.doanjava.service.PhieuNhapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/phieunhap-tong")
public class PhieuNhapTongController {
    @Autowired
    private PhieuNhapService phieuNhapService;

    @GetMapping("")
    public String list(Model model) {
        List<PhieuNhap> ds = phieuNhapService.findAll();
        model.addAttribute("dsPhieuNhapTong", ds);
        return "phieunhap/danhsach_tong";
    }
}
