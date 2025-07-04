
package baitap.doanjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import baitap.doanjava.entity.ChiTietHoaDon;
import baitap.doanjava.entity.HoaDonBanHang;
import baitap.doanjava.service.ChiTietHoaDonService;
import baitap.doanjava.service.HoaDonBanHangService;
import baitap.doanjava.service.SanPhamService;

@Controller
@RequestMapping("/chitiethoadon")
public class ChiTietHoaDonController {
    @Autowired
    private ChiTietHoaDonService chiTietHoaDonService;

    @Autowired
    private HoaDonBanHangService hoaDonBanHangService;

    @Autowired
    private SanPhamService sanPhamService;

    @GetMapping("/hoadon/{maHD}")
    public String listByMaHD(@PathVariable String maHD, Model model) {
        model.addAttribute("dsChiTiet", chiTietHoaDonService.findByMaHD(maHD));
        model.addAttribute("maHD", maHD);
        return "chitiethoadon/index";
    }

    @GetMapping("/them")
    public String showAddForm(@RequestParam("maHD") String maHD, Model model) {
        HoaDonBanHang hoaDon = hoaDonBanHangService.findById(maHD).orElse(null);
        if (hoaDon == null) {
            return "redirect:/hoadon";
        }
        ChiTietHoaDon chiTietMoi = new ChiTietHoaDon();
        chiTietMoi.setMaHD(maHD);
        chiTietMoi.setHoaDon(hoaDon);
        model.addAttribute("chiTietMoi", chiTietMoi);
        model.addAttribute("dsSanPham", sanPhamService.getAllSanPham());
        model.addAttribute("maKhoOptions", new String[] { "K1", "K2" });
        return "chitiethoadon/form_them";
    }

    @PostMapping("/luu")
    public String saveNew(@ModelAttribute("chiTietMoi") ChiTietHoaDon chiTiet, Model model) {
        String maHD = chiTiet.getMaHD();
        HoaDonBanHang hoaDon = hoaDonBanHangService.findById(maHD).orElse(null);
        if (hoaDon == null) {
            model.addAttribute("errorMessage", "Mã hóa đơn không tồn tại!");
            model.addAttribute("chiTietMoi", chiTiet);
            model.addAttribute("dsSanPham", sanPhamService.getAllSanPham());
            model.addAttribute("maKhoOptions", new String[] { "K1", "K2" });
            return "chitiethoadon/form_them";
        }
        chiTiet.setHoaDon(hoaDon);
        Long nextId = 1L;
        var maxList = chiTietHoaDonService.findAll();
        if (!maxList.isEmpty()) {
            nextId = maxList.stream().mapToLong(ChiTietHoaDon::getId).max().orElse(0L) + 1;
        }
        chiTiet.setId(nextId);
        if (chiTiet.getGiamGia() < 0) {
            model.addAttribute("errorMessage", "Giảm giá không được âm!");
            model.addAttribute("chiTietMoi", chiTiet);
            model.addAttribute("dsSanPham", sanPhamService.getAllSanPham());
            model.addAttribute("maKhoOptions", new String[] { "K1", "K2" });
            return "chitiethoadon/form_them";
        }
        // Validate maKho
        if (!"K1".equals(chiTiet.getMaKho()) && !"K2".equals(chiTiet.getMaKho())) {
            model.addAttribute("errorMessage", "Mã kho chỉ được chọn K1 hoặc K2!");
            model.addAttribute("chiTietMoi", chiTiet);
            model.addAttribute("dsSanPham", sanPhamService.getAllSanPham());
            model.addAttribute("maKhoOptions", new String[] { "K1", "K2" });
            return "chitiethoadon/form_them";
        }
        chiTietHoaDonService.save(chiTiet);
        return "redirect:/chitiethoadon/hoadon/" + maHD;
    }

    @GetMapping("/sua")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        ChiTietHoaDon chiTietSua = chiTietHoaDonService.findById(id).orElse(null);
        if (chiTietSua == null) {
            return "redirect:/chitiethoadon";
        }
        model.addAttribute("chiTietSua", chiTietSua);
        model.addAttribute("dsSanPham", sanPhamService.getAllSanPham());
        model.addAttribute("maKhoOptions", new String[] { "K1", "K2" });
        return "chitiethoadon/form_sua";
    }

    @PostMapping("/capnhat")
    public String update(@ModelAttribute("chiTietSua") ChiTietHoaDon chiTiet, Model model) {
        if (chiTiet.getId() == null) {
            model.addAttribute("errorMessage", "Không tìm thấy chi tiết hóa đơn!");
            model.addAttribute("chiTietSua", chiTiet);
            model.addAttribute("dsSanPham", sanPhamService.getAllSanPham());
            model.addAttribute("maKhoOptions", new String[] { "K1", "K2" });
            return "chitiethoadon/form_sua";
        }
        String maHD = chiTiet.getMaHD();
        HoaDonBanHang hoaDon = hoaDonBanHangService.findById(maHD).orElse(null);
        if (hoaDon == null) {
            model.addAttribute("errorMessage", "Mã hóa đơn không tồn tại!");
            model.addAttribute("chiTietSua", chiTiet);
            model.addAttribute("dsSanPham", sanPhamService.getAllSanPham());
            model.addAttribute("maKhoOptions", new String[] { "K1", "K2" });
            return "chitiethoadon/form_sua";
        }

        chiTiet.setHoaDon(hoaDon);
        if (chiTiet.getGiamGia() < 0) {
            model.addAttribute("errorMessage", "Giảm giá không được âm!");
            model.addAttribute("chiTietSua", chiTiet);
            model.addAttribute("dsSanPham", sanPhamService.getAllSanPham());
            model.addAttribute("maKhoOptions", new String[] { "K1", "K2" });
            return "chitiethoadon/form_sua";
        }

        if (!"K1".equals(chiTiet.getMaKho()) && !"K2".equals(chiTiet.getMaKho())) {
            model.addAttribute("errorMessage", "Mã kho chỉ được chọn K1 hoặc K2!");
            model.addAttribute("chiTietSua", chiTiet);
            model.addAttribute("dsSanPham", sanPhamService.getAllSanPham());
            model.addAttribute("maKhoOptions", new String[] { "K1", "K2" });
            return "chitiethoadon/form_sua";
        }
        chiTietHoaDonService.save(chiTiet);
        return "redirect:/chitiethoadon/hoadon/" + maHD;
    }

    @GetMapping("/xoa")
    public String delete(@RequestParam("id") Long id) {
        ChiTietHoaDon chiTiet = chiTietHoaDonService.findById(id).orElse(null);
        String maHD = (chiTiet != null) ? chiTiet.getMaHD() : null;
        chiTietHoaDonService.deleteById(id);
        if (maHD != null) {
            return "redirect:/chitiethoadon/hoadon/" + maHD;
        }
        return "redirect:/hoadon";
    }
}
