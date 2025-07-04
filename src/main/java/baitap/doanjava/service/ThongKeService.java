package baitap.doanjava.service;

import java.util.List;
import java.util.Map;

public interface ThongKeService {
    Map<String, Float> thongKeDoanhThuTheoThang(int year);

    Map<String, Float> thongKeDoanhThuTheoQuy(int year);

    Map<Integer, Float> thongKeDoanhThuTheoNam();

    List<Map<String, Object>> thongKeKPINhanVien(int year, int month);
}
