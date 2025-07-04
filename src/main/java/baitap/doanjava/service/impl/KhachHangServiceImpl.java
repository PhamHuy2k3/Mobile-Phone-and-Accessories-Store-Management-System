package baitap.doanjava.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baitap.doanjava.entity.KhachHang;
import baitap.doanjava.repository.KhachHangRepository;
import baitap.doanjava.service.KhachHangService;

@Service
public class KhachHangServiceImpl implements KhachHangService {
    @Autowired
    private KhachHangRepository khachHangRepository;

    @Override
    public List<KhachHang> getAll() {
        return khachHangRepository.findAll();
    }

    @Override
    public KhachHang getById(String maKH) {
        return khachHangRepository.findById(maKH).orElse(null);
    }

    @Override
    public void save(KhachHang khachHang) {
        khachHangRepository.save(khachHang);
    }

    @Override
    public void delete(String maKH) {
        khachHangRepository.deleteById(maKH);
    }

    @Override
    public List<KhachHang> search(String keyword) {
        return khachHangRepository.search(keyword);
    }

    @Override
    public List<KhachHang> findThanThiet() {
        return khachHangRepository.findKhachHangThanThiet();
    }

    @Override
    public List<KhachHang> findVangLai() {
        return khachHangRepository.findKhachHangVangLai();
    }

    @Override
    public List<KhachHang> sortByName(boolean asc) {
        return asc ? khachHangRepository.findAllOrderByHoTenKHAsc() : khachHangRepository.findAllOrderByHoTenKHDesc();
    }
}
