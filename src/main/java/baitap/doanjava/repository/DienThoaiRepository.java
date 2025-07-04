package baitap.doanjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import baitap.doanjava.entity.DienThoai;

@Repository
public interface DienThoaiRepository extends JpaRepository<DienThoai, String> {
}
