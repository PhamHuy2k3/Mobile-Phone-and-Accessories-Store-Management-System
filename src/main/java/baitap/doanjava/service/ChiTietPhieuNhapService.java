package baitap.doanjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baitap.doanjava.entity.ChiTietPhieuNhap;
import baitap.doanjava.entity.ChiTietPhieuNhapId;
import baitap.doanjava.repository.ChiTietPhieuNhapRepository;

import java.util.List;

@Service
public class ChiTietPhieuNhapService {
    @Autowired
    private ChiTietPhieuNhapRepository repository;

    public List<ChiTietPhieuNhap> getAll() {
        return repository.findAll();
    }

    public List<ChiTietPhieuNhap> getByMaPN(String maPN) {
        return repository.findByIdMaPN(maPN);
    }

    public ChiTietPhieuNhap save(ChiTietPhieuNhap ctpn) {
        return repository.save(ctpn);
    }

    public void delete(ChiTietPhieuNhapId id) {
        repository.deleteById(id);
    }

    public String generateNextMaPN() {
        Integer max = repository.findMaxMaPNNumber();
        int next = (max != null ? max : 0) + 1;
        return String.format("PN%03d", next);
    }

}
