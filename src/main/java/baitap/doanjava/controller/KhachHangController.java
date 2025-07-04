
package baitap.doanjava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import baitap.doanjava.entity.HoaDonBanHang;
import baitap.doanjava.entity.KhachHang;
import baitap.doanjava.repository.HoaDonBanHangRepository;
import baitap.doanjava.service.KhachHangService;

@Controller
@RequestMapping("/khachhang")
public class KhachHangController {
    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private HoaDonBanHangRepository hoaDonBanHangRepository;

    @GetMapping
    public String list(@RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "type", required = false) String type,
            Model model) {
        List<KhachHang> ds;
        if ("thanthiet".equals(type)) {
            ds = khachHangService.findThanThiet();
        } else if ("vanglai".equals(type)) {
            ds = khachHangService.findVangLai();
        } else if (search != null && !search.isEmpty()) {
            ds = khachHangService.search(search);
        } else if ("desc".equals(sort)) {
            ds = khachHangService.sortByName(false);
        } else {
            ds = khachHangService.sortByName(true);
        }
        model.addAttribute("dsKhachHang", ds);
        return "khachhang/index";
    }

    @GetMapping("/them")
    public String formThem(Model model) {
        model.addAttribute("khachHang", new KhachHang());
        return "khachhang/form";
    }

    @PostMapping("/luu")
    public String luu(@ModelAttribute KhachHang khachHang) {
        khachHangService.save(khachHang);
        return "redirect:/khachhang";
    }

    @GetMapping("/sua/{maKH}")
    public String formSua(@PathVariable String maKH, Model model) {
        model.addAttribute("khachHang", khachHangService.getById(maKH));
        return "khachhang/form";
    }

    @GetMapping("/xoa/{maKH}")
    public String xoa(@PathVariable String maKH) {
        khachHangService.delete(maKH);
        return "redirect:/khachhang";
    }

    @GetMapping("/lichsu/{maKH}")
    public String lichSuMuaHang(@PathVariable String maKH, Model model) {
        KhachHang kh = khachHangService.getById(maKH);
        List<HoaDonBanHang> dsHoaDon = hoaDonBanHangRepository.findByMaKH(maKH);
        model.addAttribute("khachHang", kh);
        model.addAttribute("dsHoaDon", dsHoaDon);
        return "khachhang/lichsu";
    }
}
