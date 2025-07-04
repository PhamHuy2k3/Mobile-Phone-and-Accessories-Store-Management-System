package baitap.doanjava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import baitap.doanjava.entity.HoaDonBanHang;

public interface HoaDonBanHangRepository extends JpaRepository<HoaDonBanHang, String> {
    List<HoaDonBanHang> findByMaKH(String maKH);

    // Lấy hóa đơn có mã lớn nhất (theo số thứ tự sau HD)
    @Query("SELECT h FROM HoaDonBanHang h WHERE h.maHD LIKE 'HD%' ORDER BY h.maHD DESC")
    List<HoaDonBanHang> findTopByOrderByMaHDDesc();

    @Query("SELECT SUM(h.tongTien) FROM HoaDonBanHang h WHERE YEAR(h.ngayLap) = :year AND MONTH(h.ngayLap) = :month AND h.trangThai = 'Đã thanh toán'")
    Float tongDoanhThuTheoThang(@Param("year") int year, @Param("month") int month);

    @Query("SELECT SUM(h.tongTien) FROM HoaDonBanHang h WHERE YEAR(h.ngayLap) = :year AND QUARTER(h.ngayLap) = :quy AND h.trangThai = 'Đã thanh toán'")
    Float tongDoanhThuTheoQuy(@Param("year") int year, @Param("quy") int quy);

    @Query("SELECT DISTINCT YEAR(h.ngayLap) FROM HoaDonBanHang h ORDER BY YEAR(h.ngayLap)")
    List<Integer> getAllYears();

    @Query("SELECT SUM(h.tongTien) FROM HoaDonBanHang h WHERE YEAR(h.ngayLap) = :year AND h.trangThai = 'Đã thanh toán'")
    Float tongDoanhThuTheoNam(@Param("year") int year);

    @Query("SELECT h.maNV as maNV, COUNT(h.maHD) as soHoaDon, SUM(h.tongTien) as tongDoanhThu FROM HoaDonBanHang h WHERE YEAR(h.ngayLap) = :year AND MONTH(h.ngayLap) = :month AND h.trangThai = 'Đã thanh toán' GROUP BY h.maNV")
    List<Object[]> kpiNhanVien(@Param("year") int year, @Param("month") int month);
}
