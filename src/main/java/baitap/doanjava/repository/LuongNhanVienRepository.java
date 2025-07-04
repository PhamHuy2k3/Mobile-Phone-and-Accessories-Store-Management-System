package baitap.doanjava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import baitap.doanjava.entity.LuongNhanVien;

public interface LuongNhanVienRepository extends JpaRepository<LuongNhanVien, String> {
    List<LuongNhanVien> findByMaNV(String maNV);
}
