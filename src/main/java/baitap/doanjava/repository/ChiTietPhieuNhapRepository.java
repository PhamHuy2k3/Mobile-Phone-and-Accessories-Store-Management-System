package baitap.doanjava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import baitap.doanjava.entity.ChiTietPhieuNhap;
import baitap.doanjava.entity.ChiTietPhieuNhapId;

public interface ChiTietPhieuNhapRepository extends JpaRepository<ChiTietPhieuNhap, ChiTietPhieuNhapId> {
    @Query("SELECT MAX(CAST(SUBSTRING(c.id.maPN, 3, LEN(c.id.maPN)-2) AS int)) FROM ChiTietPhieuNhap c WHERE c.id.maPN LIKE 'PN%'")
    Integer findMaxMaPNNumber();

    List<ChiTietPhieuNhap> findByIdMaPN(String maPN);
}
