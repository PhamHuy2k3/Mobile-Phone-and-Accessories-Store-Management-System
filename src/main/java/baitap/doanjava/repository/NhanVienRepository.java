package baitap.doanjava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import baitap.doanjava.entity.NhanVien;

public interface NhanVienRepository extends JpaRepository<NhanVien, String> {
    List<NhanVien> findByVaiTro(String vaiTro);
}
