package baitap.doanjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import baitap.doanjava.entity.MayTinhBang;

@Repository
public interface MayTinhBangRepository extends JpaRepository<MayTinhBang, String> {
}
