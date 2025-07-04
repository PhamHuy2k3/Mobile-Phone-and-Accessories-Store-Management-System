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

import baitap.doanjava.entity.DienThoai;
import baitap.doanjava.entity.MayTinhBang;
import baitap.doanjava.entity.PhuKien;
import baitap.doanjava.service.SanPhamService;

@Controller
@RequestMapping("/sanpham")
public class SanPhamController {
    @Autowired
    private SanPhamService sanPhamService;

    @GetMapping
    public String danhSach(
            @RequestParam(value = "loai", required = false) String loaiSP,
            @RequestParam(value = "ncc", required = false) String maNCC,
            @RequestParam(value = "ten", required = false) String tenSP,
            @RequestParam(value = "giaTu", required = false) Integer giaTu,
            @RequestParam(value = "giaDen", required = false) Integer giaDen,
            Model model) {
        model.addAttribute("loaiSP", loaiSP);
        model.addAttribute("maNCC", maNCC);
        List<String> dsNCC = sanPhamService.getAllMaNCC();
        model.addAttribute("dsNCC", dsNCC);
        model.addAttribute("dsDienThoai", sanPhamService.timKiemDienThoai(tenSP, giaTu, giaDen, maNCC, loaiSP));
        model.addAttribute("dsMayTinhBang", sanPhamService.timKiemMayTinhBang(tenSP, giaTu, giaDen, maNCC, loaiSP));
        model.addAttribute("dsPhuKien", sanPhamService.timKiemPhuKien(tenSP, giaTu, giaDen, maNCC, loaiSP));
        return "sanpham/index";
    }

    @GetMapping("/them/dienthoai")
    public String formThemDienThoai(Model model) {
        DienThoai dt = new DienThoai();
        dt.setMaSP(sanPhamService.generateNextMaSP("dienthoai"));
        model.addAttribute("sanPham", dt);
        model.addAttribute("loaiSP", "dienthoai");
        model.addAttribute("dsNCC", sanPhamService.getAllMaNCC());
        return "sanpham/them";
    }

    @GetMapping("/them/maytinhbang")
    public String formThemMayTinhBang(Model model) {
        MayTinhBang mtb = new MayTinhBang();
        mtb.setMaSP(sanPhamService.generateNextMaSP("maytinhbang"));
        model.addAttribute("sanPham", mtb);
        model.addAttribute("loaiSP", "maytinhbang");
        model.addAttribute("dsNCC", sanPhamService.getAllMaNCC());
        return "sanpham/them";
    }

    @GetMapping("/them/phukien")
    public String formThemPhuKien(Model model) {
        PhuKien pk = new PhuKien();
        pk.setMaSP(sanPhamService.generateNextMaSP("phukien"));
        model.addAttribute("sanPham", pk);
        model.addAttribute("loaiSP", "phukien");
        model.addAttribute("dsNCC", sanPhamService.getAllMaNCC());
        return "sanpham/them";
    }

    @PostMapping("/luu/dienthoai")
    public String luuDienThoai(@ModelAttribute DienThoai sanPham) {
        sanPham.setMaLoaiSP("LSP1");
        sanPhamService.saveDienThoai(sanPham);
        return "redirect:/sanpham";
    }

    @PostMapping("/luu/maytinhbang")
    public String luuMayTinhBang(@ModelAttribute MayTinhBang sanPham) {
        sanPham.setMaLoaiSP("LSP2");
        sanPhamService.saveMayTinhBang(sanPham);
        return "redirect:/sanpham";
    }

    @PostMapping("/luu/phukien")
    public String luuPhuKien(@ModelAttribute PhuKien sanPham) {
        sanPham.setMaLoaiSP("LSP3");
        sanPhamService.savePhuKien(sanPham);
        return "redirect:/sanpham";
    }

    @GetMapping("/sua/dienthoai/{maSP}")
    public String formSuaDienThoai(@PathVariable String maSP, Model model) {
        model.addAttribute("sanPham", sanPhamService.getDienThoaiById(maSP));
        model.addAttribute("loaiSP", "dienthoai");
        return "sanpham/them";
    }

    @GetMapping("/sua/maytinhbang/{maSP}")
    public String formSuaMayTinhBang(@PathVariable String maSP, Model model) {
        model.addAttribute("sanPham", sanPhamService.getMayTinhBangById(maSP));
        model.addAttribute("loaiSP", "maytinhbang");
        return "sanpham/them";
    }

    @GetMapping("/sua/phukien/{maSP}")
    public String formSuaPhuKien(@PathVariable String maSP, Model model) {
        model.addAttribute("sanPham", sanPhamService.getPhuKienById(maSP));
        model.addAttribute("loaiSP", "phukien");
        return "sanpham/them";
    }

    @GetMapping("/xoa/dienthoai/{maSP}")
    public String xoaDienThoai(@PathVariable String maSP) {
        sanPhamService.deleteDienThoai(maSP);
        return "redirect:/sanpham";
    }

    @GetMapping("/xoa/maytinhbang/{maSP}")
    public String xoaMayTinhBang(@PathVariable String maSP) {
        sanPhamService.deleteMayTinhBang(maSP);
        return "redirect:/sanpham";
    }

    @GetMapping("/xoa/phukien/{maSP}")
    public String xoaPhuKien(@PathVariable String maSP) {
        sanPhamService.deletePhuKien(maSP);
        return "redirect:/sanpham";
    }
}
