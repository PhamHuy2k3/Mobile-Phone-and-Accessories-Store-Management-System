
package baitap.doanjava.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baitap.doanjava.entity.ChiTietHoaDon;
import baitap.doanjava.repository.ChiTietHoaDonRepository;

@Service
public class ChiTietHoaDonService {
    @Autowired
    private ChiTietHoaDonRepository chiTietHoaDonRepository;

    public List<ChiTietHoaDon> findByMaHD(String maHD) {
        return chiTietHoaDonRepository.findByHoaDonMaHD(maHD);
    }

    public List<ChiTietHoaDon> findAll() {
        return chiTietHoaDonRepository.findAll();
    }

    public Optional<ChiTietHoaDon> findById(Long id) {
        return chiTietHoaDonRepository.findById(id);
    }

    public ChiTietHoaDon save(ChiTietHoaDon chiTiet) {
        return chiTietHoaDonRepository.save(chiTiet);
    }

    public void deleteById(Long id) {
        chiTietHoaDonRepository.deleteById(id);
    }
}
