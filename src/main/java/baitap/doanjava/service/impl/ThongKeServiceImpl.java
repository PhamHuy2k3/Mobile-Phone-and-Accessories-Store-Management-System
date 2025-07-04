package baitap.doanjava.service.impl;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import baitap.doanjava.entity.HoaDonBanHang;
import baitap.doanjava.repository.HoaDonBanHangRepository;
import baitap.doanjava.service.ThongKeService;

@Service
public class ThongKeServiceImpl implements ThongKeService {
    @Autowired
    private HoaDonBanHangRepository hoaDonBanHangRepository;

    @Override
    public Map<String, Float> thongKeDoanhThuTheoThang(int year) {
        Map<String, Float> result = new LinkedHashMap<>();
        List<HoaDonBanHang> ds = hoaDonBanHangRepository.findAll();
        for (int month = 1; month <= 12; month++) {
            float sum = 0f;
            for (HoaDonBanHang hd : ds) {
                if (hd.getNgayLap() != null && hd.getTrangThai() != null
                        && hd.getTrangThai().trim().equalsIgnoreCase("Đã thanh toán")
                        && hd.getNgayLap().toLocalDate().getYear() == year
                        && hd.getNgayLap().toLocalDate().getMonthValue() == month) {
                    sum += hd.getTongTien();
                }
            }
            result.put(String.format("%02d/%d", month, year), sum);
        }
        return result;
    }

    @Override
    public Map<String, Float> thongKeDoanhThuTheoQuy(int year) {
        Map<String, Float> result = new LinkedHashMap<>();
        List<HoaDonBanHang> ds = hoaDonBanHangRepository.findAll();
        for (int quy = 1; quy <= 4; quy++) {
            float sum = 0f;
            for (HoaDonBanHang hd : ds) {
                if (hd.getNgayLap() != null && hd.getTrangThai() != null
                        && hd.getTrangThai().trim().equalsIgnoreCase("Đã thanh toán")
                        && hd.getNgayLap().toLocalDate().getYear() == year) {
                    int month = hd.getNgayLap().toLocalDate().getMonthValue();
                    int quyHD = (month - 1) / 3 + 1;
                    if (quyHD == quy) {
                        sum += hd.getTongTien();
                    }
                }
            }
            result.put("Q" + quy + "/" + year, sum);
        }
        return result;
    }

    @Override
    public Map<Integer, Float> thongKeDoanhThuTheoNam() {
        Map<Integer, Float> result = new LinkedHashMap<>();
        List<HoaDonBanHang> ds = hoaDonBanHangRepository.findAll();
        Set<Integer> years = new TreeSet<>();
        for (HoaDonBanHang hd : ds) {
            if (hd.getNgayLap() != null && hd.getTrangThai() != null
                    && hd.getTrangThai().trim().equalsIgnoreCase("Đã thanh toán")) {
                years.add(hd.getNgayLap().toLocalDate().getYear());
            }
        }
        for (Integer year : years) {
            float sum = 0f;
            for (HoaDonBanHang hd : ds) {
                if (hd.getNgayLap() != null && hd.getTrangThai() != null
                        && hd.getTrangThai().trim().equalsIgnoreCase("Đã thanh toán")
                        && hd.getNgayLap().toLocalDate().getYear() == year) {
                    sum += hd.getTongTien();
                }
            }
            result.put(year, sum);
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> thongKeKPINhanVien(int year, int month) {
        List<HoaDonBanHang> ds = hoaDonBanHangRepository.findAll();
        Map<String, Map<String, Object>> kpiMap = new LinkedHashMap<>();
        for (HoaDonBanHang hd : ds) {
            if (hd.getNgayLap() != null && hd.getTrangThai() != null
                    && hd.getTrangThai().trim().equalsIgnoreCase("Đã thanh toán")
                    && hd.getNgayLap().toLocalDate().getYear() == year
                    && hd.getNgayLap().toLocalDate().getMonthValue() == month) {
                String maNV = hd.getMaNV();
                Map<String, Object> kpi = kpiMap.getOrDefault(maNV, new HashMap<>());
                kpi.put("maNV", maNV);
                kpi.put("soHoaDon", ((int) kpi.getOrDefault("soHoaDon", 0)) + 1);
                kpi.put("tongDoanhThu", ((float) kpi.getOrDefault("tongDoanhThu", 0f)) + hd.getTongTien());
                kpiMap.put(maNV, kpi);
            }
        }
        return new ArrayList<>(kpiMap.values());
    }
}
