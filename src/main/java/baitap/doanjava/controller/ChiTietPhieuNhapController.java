package baitap.doanjava.controller;

import baitap.doanjava.entity.ChiTietPhieuNhap;
import baitap.doanjava.entity.ChiTietPhieuNhapId;
import baitap.doanjava.service.ChiTietPhieuNhapService;
import baitap.doanjava.service.PhieuNhapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import baitap.doanjava.service.SanPhamService;

@Controller
@RequestMapping("/phieunhap")
public class ChiTietPhieuNhapController {
    @Autowired
    private ChiTietPhieuNhapService phieuNhapService;

    @Autowired
    private PhieuNhapService phieuNhapTongService;

    @Autowired
    private SanPhamService sanPhamService;

    @GetMapping("")
    public String list(Model model) {
        List<ChiTietPhieuNhap> ds = phieuNhapService.getAll();
        model.addAttribute("dsPhieuNhap", ds);
        return "phieunhap/index";
    }

    @GetMapping("/them")
    public String them(Model model, @RequestParam(value = "maSP", required = false) String maSP) {
        ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap();
        ctpn.setId(new ChiTietPhieuNhapId());
        if (maSP != null) {
            ctpn.getId().setMaSP(maSP);
        }
        model.addAttribute("phieuNhap", ctpn);
        model.addAttribute("dsPhieuNhapTong", phieuNhapTongService.findAll());
        model.addAttribute("dsSanPham", sanPhamService.getAllSanPham());
        // Lấy đơn giá sản phẩm nếu đã chọn mã sản phẩm
        Double donGiaSanPham = 0.0;
        if (maSP != null) {
            var sp = sanPhamService.getAllSanPham().stream()
                    .filter(s -> s.getMaSP().equals(maSP))
                    .findFirst().orElse(null);
            if (sp != null) {
                donGiaSanPham = (double) sp.getDonGia();
            }
        }
        model.addAttribute("donGiaSanPham", donGiaSanPham);
        return "phieunhap/them";
    }

    @PostMapping("/luu")
    public String luu(@ModelAttribute("phieuNhap") ChiTietPhieuNhap ctpn) {
        String maPN = ctpn.getMaPN();
        String maSP = ctpn.getMaSP();
        String maNCC = "NCC01";
        String maNV = "NV01";
        // Lấy đơn giá đúng theo mã sản phẩm
        Double donGia = null;
        var sp = sanPhamService.getAllSanPham().stream()
                .filter(s -> s.getMaSP().equals(maSP))
                .findFirst().orElse(null);
        if (sp != null) {
            donGia = (double) sp.getDonGia();
            ctpn.setDonGia(donGia);
        }
        phieuNhapTongService.createIfNotExists(maPN, maNCC, maNV);
        phieuNhapService.save(ctpn);
        return "redirect:/phieunhap";
    }

    @GetMapping("/xoa")
    public String xoa(@RequestParam("maPN") String maPN, @RequestParam("maSP") String maSP,
            @RequestParam("maKho") String maKho) {
        ChiTietPhieuNhapId id = new ChiTietPhieuNhapId();
        id.setMaPN(maPN);
        id.setMaSP(maSP);
        id.setMaKho(maKho);
        phieuNhapService.delete(id);
        return "redirect:/phieunhap";
    }
}
