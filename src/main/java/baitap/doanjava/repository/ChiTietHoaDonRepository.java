package baitap.doanjava.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import baitap.doanjava.entity.ChiTietHoaDon;

public interface ChiTietHoaDonRepository extends JpaRepository<ChiTietHoaDon, Long> {
    // Lấy chi tiết hóa đơn có id lớn nhất
    @Query("SELECT c FROM ChiTietHoaDon c ORDER BY c.id DESC")
    List<ChiTietHoaDon> findTopByOrderByIdDesc();

    @Query("SELECT c FROM ChiTietHoaDon c WHERE c.hoaDon.maHD = :maHD")
    List<ChiTietHoaDon> findByHoaDonMaHD(@Param("maHD") String maHD);
}
