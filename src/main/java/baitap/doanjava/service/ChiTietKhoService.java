package baitap.doanjava.service;

import baitap.doanjava.entity.ChiTietKho;
import baitap.doanjava.entity.ChiTietKhoId;
import baitap.doanjava.repository.ChiTietKhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChiTietKhoService {
    @Autowired
    private ChiTietKhoRepository chiTietKhoRepository;

    public List<ChiTietKho> findAll() {
        return chiTietKhoRepository.findAll();
    }

    public Optional<ChiTietKho> findById(ChiTietKhoId id) {
        return chiTietKhoRepository.findById(id);
    }

    public ChiTietKho save(ChiTietKho chiTietKho) {
        return chiTietKhoRepository.save(chiTietKho);
    }

    public void deleteById(ChiTietKhoId id) {
        chiTietKhoRepository.deleteById(id);
    }

    public List<ChiTietKho> findSanPhamCanNhap(int nguong) {
        return chiTietKhoRepository.findSanPhamCanNhap(nguong);
    }
}
