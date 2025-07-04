package baitap.doanjava.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "SanPham", schema = "dbo")
public class MayTinhBang extends SanPham {
    @Override
    public String getLoaiSanPham() {
        return "LSP2";
    }
}
