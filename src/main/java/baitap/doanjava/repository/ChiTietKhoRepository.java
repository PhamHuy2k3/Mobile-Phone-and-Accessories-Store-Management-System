package baitap.doanjava.repository;

import baitap.doanjava.entity.ChiTietKho;
import baitap.doanjava.entity.ChiTietKhoId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChiTietKhoRepository extends JpaRepository<ChiTietKho, ChiTietKhoId> {
    // Sản phẩm tồn kho dưới ngưỡng cảnh báo (ví dụ: 10)
    @Query("SELECT c FROM ChiTietKho c WHERE c.soLuongTon < :nguong")
    List<ChiTietKho> findSanPhamCanNhap(@Param("nguong") Integer nguong);
}
