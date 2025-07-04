package baitap.doanjava.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baitap.doanjava.entity.LuongNhanVien;
import baitap.doanjava.entity.NhanVien;
import baitap.doanjava.repository.LuongNhanVienRepository;

@Service
public class LuongNhanVienService {
    @Autowired
    private LuongNhanVienRepository luongNhanVienRepository;
    @Autowired
    private NhanVienService nhanVienService;

    public List<LuongNhanVien> getBangLuongByMaNV(String maNV) {
        return luongNhanVienRepository.findByMaNV(maNV);
    }

    public List<LuongNhanVien> getAllBangLuong() {
        return luongNhanVienRepository.findAll();
    }

    public LuongNhanVien getById(String maLuong) {
        return luongNhanVienRepository.findById(maLuong).orElse(null);
    }

    public LuongNhanVien save(LuongNhanVien luong) {
        // Tự động set phụ cấp dựa vào vai trò nhân viên
        if (luong.getMaNV() != null) {
            NhanVien nv = nhanVienService.getById(luong.getMaNV());
            if (nv != null) {
                luong.setPhuCap(nv.getPhuCap());
            }
        }
        return luongNhanVienRepository.save(luong);
    }

    public void delete(String maLuong) {
        luongNhanVienRepository.deleteById(maLuong);
    }

    public List<LuongNhanVien> filterLuongNhanVien(String thang, String vaiTro, String tuKhoa) {
        List<LuongNhanVien> ds = luongNhanVienRepository.findAll();
        if (thang != null && !thang.isEmpty()) {
            ds = ds.stream().filter(l -> l.locTheoThang(thang)).collect(Collectors.toList());
        }
        if (vaiTro != null && !vaiTro.isEmpty()) {

            List<String> maNVs = nhanVienService.getByVaiTro(vaiTro).stream().map(NhanVien::getMaNV)
                    .collect(Collectors.toList());
            ds = ds.stream().filter(l -> maNVs.contains(l.getMaNV())).collect(Collectors.toList());
        }
        if (tuKhoa != null && !tuKhoa.isEmpty()) {

            List<NhanVien> allNV = nhanVienService.getAll();
            ds = ds.stream().filter(l -> {
                boolean matchMa = l.getMaNV() != null && l.getMaNV().toLowerCase().contains(tuKhoa.toLowerCase());

                String ten = allNV.stream().filter(nv -> nv.getMaNV().equals(l.getMaNV())).map(NhanVien::getHoTenNV)
                        .findFirst().orElse("");
                boolean matchTen = ten.toLowerCase().contains(tuKhoa.toLowerCase());
                return matchMa || matchTen;
            }).collect(Collectors.toList());
        }
        return ds;
    }
}
