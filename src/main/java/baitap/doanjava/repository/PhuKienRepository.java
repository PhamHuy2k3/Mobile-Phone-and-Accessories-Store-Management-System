package baitap.doanjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import baitap.doanjava.entity.PhuKien;

@Repository
public interface PhuKienRepository extends JpaRepository<PhuKien, String> {
}
