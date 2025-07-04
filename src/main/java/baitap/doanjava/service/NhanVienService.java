package baitap.doanjava.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baitap.doanjava.entity.NhanVien;
import baitap.doanjava.entity.NhanVienBanHang;
import baitap.doanjava.repository.NhanVienRepository;

@Service
public class NhanVienService {
    @Autowired
    private NhanVienRepository nhanVienRepository;

    public List<NhanVien> getAll() {
        return nhanVienRepository.findAll();
    }

    public List<NhanVienBanHang> getAllBanHang() {
        return nhanVienRepository.findByVaiTro("MV2").stream()
                .map(NhanVienBanHang::new)
                .collect(Collectors.toList());
    }

    public List<NhanVien> getByVaiTro(String vaiTro) {
        return nhanVienRepository.findByVaiTro(vaiTro);
    }

    public NhanVien getById(String id) {
        return nhanVienRepository.findById(id).orElse(null);
    }

    public NhanVien save(NhanVien nv) {
        if (nv.getHoaHong() == null) {
            nv.setHoaHong(0.0);
        }
        return nhanVienRepository.save(nv);
    }

    public void delete(String id) {
        nhanVienRepository.deleteById(id);
    }
}