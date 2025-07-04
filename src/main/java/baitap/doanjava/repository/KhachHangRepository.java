package baitap.doanjava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import baitap.doanjava.entity.KhachHang;

public interface KhachHangRepository extends JpaRepository<KhachHang, String> {
    @Query("SELECT k FROM KhachHang k WHERE k.hoTenKH LIKE %:keyword% OR k.maKH LIKE %:keyword% OR k.diaChi LIKE %:keyword% OR k.soDienThoai LIKE %:keyword%")
    List<KhachHang> search(@Param("keyword") String keyword);

    @Query("SELECT k FROM KhachHang k ORDER BY k.hoTenKH ASC")
    List<KhachHang> findAllOrderByHoTenKHAsc();

    @Query("SELECT k FROM KhachHang k ORDER BY k.hoTenKH DESC")
    List<KhachHang> findAllOrderByHoTenKHDesc();

    // Khách hàng thân thiết: mua >= 2 hóa đơn
    @Query("SELECT k FROM KhachHang k WHERE (SELECT COUNT(h) FROM HoaDonBanHang h WHERE h.maKH = k.maKH) >= 2")
    List<KhachHang> findKhachHangThanThiet();

    // Khách hàng vãng lai: mua < 2 hóa đơn
    @Query("SELECT k FROM KhachHang k WHERE (SELECT COUNT(h) FROM HoaDonBanHang h WHERE h.maKH = k.maKH) < 2")
    List<KhachHang> findKhachHangVangLai();
}
