package baitap.doanjava.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baitap.doanjava.entity.HoaDonBanHang;
import baitap.doanjava.repository.HoaDonBanHangRepository;

@Service
public class HoaDonBanHangService {
    @Autowired
    private HoaDonBanHangRepository hoaDonBanHangRepository;

    // Sinh mã hóa đơn tự động: HD + (số lớn nhất + 1)
    public String generateNextMaHD() {
        List<HoaDonBanHang> list = hoaDonBanHangRepository.findTopByOrderByMaHDDesc();
        if (list != null && !list.isEmpty()) {
            String lastMaHD = list.get(0).getMaHD();
            try {
                String numberPart = lastMaHD.substring(2); // Bỏ "HD"
                int num = Integer.parseInt(numberPart);
                return String.format("HD%d", num + 1);
            } catch (Exception e) {
                // Nếu lỗi format, trả về HD1
                return "HD1";
            }
        }
        return "HD1";
    }

    public List<HoaDonBanHang> findAll() {
        return hoaDonBanHangRepository.findAll();
    }

    public Optional<HoaDonBanHang> findById(String id) {
        return hoaDonBanHangRepository.findById(id);
    }

    public HoaDonBanHang save(HoaDonBanHang hoaDon) {
        return hoaDonBanHangRepository.save(hoaDon);
    }

    public void deleteById(String id) {
        hoaDonBanHangRepository.deleteById(id);
    }
}
