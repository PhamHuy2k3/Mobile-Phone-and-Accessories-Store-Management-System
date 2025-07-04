package baitap.doanjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import baitap.doanjava.entity.HoaDonBanHang;
import baitap.doanjava.service.HoaDonBanHangService;
import baitap.doanjava.service.NhanVienService;
import baitap.doanjava.service.KhachHangService;

@Controller
@RequestMapping("/hoadon")
public class HoaDonBanHangController {
    @Autowired
    private HoaDonBanHangService hoaDonBanHangService;

    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private KhachHangService khachHangService;

    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("dsHoaDon", hoaDonBanHangService.findAll());
        return "hoadon/index";
    }

    @GetMapping("/them")
    public String showAddForm(Model model) {
        HoaDonBanHang hoaDon = new HoaDonBanHang();
        hoaDon.setMaHD(hoaDonBanHangService.generateNextMaHD());
        model.addAttribute("hoaDon", hoaDon);
        model.addAttribute("dsNhanVien", nhanVienService.getAll());
        model.addAttribute("dsKhachHang", khachHangService.getAll());
        return "hoadon/form";
    }

    @PostMapping("/luu")
    public String save(@ModelAttribute("hoaDon") HoaDonBanHang hoaDon) {
        hoaDonBanHangService.save(hoaDon);
        return "redirect:/hoadon";
    }

    @GetMapping("/xoa/{id}")
    public String delete(@PathVariable String id) {
        hoaDonBanHangService.deleteById(id);
        return "redirect:/hoadon";
    }

    @GetMapping("/sua/{id}")
    public String edit(@PathVariable String id, Model model) {
        HoaDonBanHang hoaDon = hoaDonBanHangService.findById(id).orElse(null);
        if (hoaDon != null) {
            model.addAttribute("hoaDon", hoaDon);
            model.addAttribute("dsNhanVien", nhanVienService.getAll());
            model.addAttribute("dsKhachHang", khachHangService.getAll());
            return "hoadon/form";
        }
        return "redirect:/hoadon";
    }
}
