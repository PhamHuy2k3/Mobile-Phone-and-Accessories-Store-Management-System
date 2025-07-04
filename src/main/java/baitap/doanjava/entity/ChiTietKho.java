package baitap.doanjava.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ChiTietKho")
public class ChiTietKho {
    @EmbeddedId
    private ChiTietKhoId id;

    @Column(name = "SoLuongTon")
    private Integer soLuongTon;

    public ChiTietKhoId getId() {
        return id;
    }

    public void setId(ChiTietKhoId id) {
        this.id = id;
    }

    public Integer getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(Integer soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public String getMaSP() {
        return id != null ? id.getMaSP() : null;
    }

    public String getMaKho() {
        return id != null ? id.getMaKho() : null;
    }
}
