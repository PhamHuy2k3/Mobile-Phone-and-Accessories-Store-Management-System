package baitap.doanjava.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ChiTietPhieuNhap")
public class ChiTietPhieuNhap {
    @EmbeddedId
    private ChiTietPhieuNhapId id;

    @Column(name = "SoLuong")
    private Integer soLuong;

    @Column(name = "DonGia")
    private Double donGia;

    // Getter/setter
    public ChiTietPhieuNhapId getId() {
        return id;
    }

    public void setId(ChiTietPhieuNhapId id) {
        this.id = id;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    // Tiện ích
    public String getMaPN() {
        return id != null ? id.getMaPN() : null;
    }

    public String getMaSP() {
        return id != null ? id.getMaSP() : null;
    }

    public String getMaKho() {
        return id != null ? id.getMaKho() : null;
    }
}
