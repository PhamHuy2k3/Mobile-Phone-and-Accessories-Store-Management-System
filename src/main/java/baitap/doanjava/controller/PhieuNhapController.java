package baitap.doanjava.controller;

import baitap.doanjava.entity.PhieuNhap;
import baitap.doanjava.service.PhieuNhapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/phieunhap")
public class PhieuNhapController {
    @Autowired
    private PhieuNhapService phieuNhapService;

    @GetMapping("/tao")
    public String taoForm(Model model) {
        model.addAttribute("phieuNhap", new PhieuNhap());
        return "phieunhap/tao";
    }

    @PostMapping("/tao")
    public String taoPhieuNhap(@ModelAttribute PhieuNhap phieuNhap) {
        phieuNhapService.save(phieuNhap);
        return "redirect:/phieunhap";
    }
}
