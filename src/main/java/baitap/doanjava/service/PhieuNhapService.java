package baitap.doanjava.service;

import baitap.doanjava.entity.PhieuNhap;
import baitap.doanjava.repository.PhieuNhapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PhieuNhapService {
    @Autowired
    private PhieuNhapRepository repository;

    public PhieuNhap save(PhieuNhap pn) {
        return repository.save(pn);
    }

    public Optional<PhieuNhap> findById(String maPN) {
        return repository.findById(maPN);
    }

    public boolean existsById(String maPN) {
        return repository.existsById(maPN);
    }

    // Tạo mới bản ghi tổng nếu chưa có
    public PhieuNhap createIfNotExists(String maPN, String maNCC, String maNV) {
        return repository.findById(maPN).orElseGet(() -> {
            PhieuNhap pn = new PhieuNhap();
            pn.setMaPN(maPN);
            pn.setMaNCC(maNCC);
            pn.setMaNV(maNV);
            pn.setNgayNhap(new Date());
            // pn.setTongTien(0.0); // Đã xóa thuộc tính tongTien
            return repository.save(pn);
        });
    }

    public List<PhieuNhap> findAll() {
        return repository.findAll();
    }
}
