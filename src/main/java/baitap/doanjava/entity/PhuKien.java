package baitap.doanjava.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "SanPham", schema = "dbo")
public class PhuKien extends SanPham {
    @Override
    public String getLoaiSanPham() {
        return "LSP3";
    }
}
