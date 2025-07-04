package baitap.doanjava.service;

import java.util.List;

import baitap.doanjava.entity.KhachHang;

public interface KhachHangService {
    List<KhachHang> getAll();

    KhachHang getById(String maKH);

    void save(KhachHang khachHang);

    void delete(String maKH);

    List<KhachHang> search(String keyword);

    List<KhachHang> findThanThiet();

    List<KhachHang> findVangLai();

    List<KhachHang> sortByName(boolean asc);
}
