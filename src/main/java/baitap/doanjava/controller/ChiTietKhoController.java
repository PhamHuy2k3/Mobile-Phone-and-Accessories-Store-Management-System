package baitap.doanjava.controller;

import baitap.doanjava.entity.ChiTietKho;
import baitap.doanjava.entity.ChiTietKhoId;
import baitap.doanjava.service.ChiTietKhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/kho")
public class ChiTietKhoController {
    @Autowired
    private ChiTietKhoService chiTietKhoService;

    @GetMapping("")
    public String listKho(Model model) {
        List<ChiTietKho> list = chiTietKhoService.findAll();
        model.addAttribute("listKho", list);
        return "kho/index";
    }

    @GetMapping("/goiy-nhaphang")
    public String goiYNhapHang(Model model) {
        // Giả lập dữ liệu doanh số và tồn kho, thực tế sẽ lấy từ DB/service
        // Map<MaSP, doanh số bán ra kỳ gần nhất>
        java.util.Map<String, Integer> salesData = new java.util.HashMap<>();
        java.util.Map<String, Integer> currentStock = new java.util.HashMap<>();
        for (ChiTietKho kho : chiTietKhoService.findAll()) {
            String maSP = kho.getId().getMaSP();
            currentStock.put(maSP, kho.getSoLuongTon());
            // Giả lập: doanh số bán ra = 10 cho mỗi sản phẩm
            salesData.put(maSP, 10);
        }
        // Gọi service gợi ý nhập hàng
        baitap.doanjava.service.StockSuggestionService suggestionService = new baitap.doanjava.service.StockSuggestionService();
        java.util.Map<String, Integer> suggestions = suggestionService.suggestStock(salesData, currentStock);
        model.addAttribute("suggestions", suggestions);
        return "kho/goiy_nhaphang";
    }

    @GetMapping("/nhaphang")
    public String nhapHang() {
        return "redirect:/phieunhap/them";
    }
}
