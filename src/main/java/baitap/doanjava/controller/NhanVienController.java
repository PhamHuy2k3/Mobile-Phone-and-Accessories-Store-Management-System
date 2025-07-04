package baitap.doanjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import baitap.doanjava.entity.NhanVien;
import baitap.doanjava.service.NhanVienService;

@Controller
@RequestMapping("/nhanvien")
public class NhanVienController {
    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("dsNhanVien", nhanVienService.getAll());
        model.addAttribute("loai", "tatca");
        return "nhanvien/index";
    }

    @GetMapping("/mv1")
    public String quanLy(Model model) {
        model.addAttribute("dsNhanVien", nhanVienService.getByVaiTro("Quản lý"));
        model.addAttribute("loaiNV", "Quản lý");
        model.addAttribute("loai", "mv1");
        return "nhanvien/index";
    }

    @GetMapping("/mv2")
    public String banHang(Model model) {
        model.addAttribute("dsNhanVien", nhanVienService.getByVaiTro("Nhân viên bán hàng"));
        model.addAttribute("loaiNV", "Nhân viên bán hàng");
        model.addAttribute("loai", "mv2");
        return "nhanvien/index";
    }

    @GetMapping("/mv3")
    public String kho(Model model) {
        model.addAttribute("dsNhanVien", nhanVienService.getByVaiTro("Nhân viên kho"));
        model.addAttribute("loaiNV", "Nhân viên kho");
        model.addAttribute("loai", "mv3");
        return "nhanvien/index";
    }

    @GetMapping("/them")
    public String them(Model model) {
        // Sử dụng NhanVien để khởi tạo form trung lập
        model.addAttribute("nhanVien", new NhanVien());
        return "nhanvien/them";
    }

    @PostMapping("/luu")
    public String luu(@ModelAttribute NhanVien nhanVien) {
        nhanVienService.save(nhanVien);
        return "redirect:/nhanvien";
    }

    @GetMapping("/xoa/{id}")
    public String xoa(@PathVariable("id") String id) {
        nhanVienService.delete(id);
        return "redirect:/nhanvien";
    }

    @GetMapping("/sua/{id}")
    public String sua(@PathVariable("id") String id, Model model) {
        NhanVien nhanVien = nhanVienService.getById(id);
        if (nhanVien != null) {
            model.addAttribute("nhanVien", nhanVien);
            return "nhanvien/them";
        }
        return "redirect:/nhanvien";
    }
}