package baitap.doanjava.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ChiTietHoaDon")

public class ChiTietHoaDon {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "MaHD")
    private String maHD;

    @Column(name = "MaSP")
    private String maSP;

    @Column(name = "SoLuong")
    private int soLuong;

    @Column(name = "MaKho")
    private String maKho;

    @Column(name = "DonGia")
    private float donGia;

    @Column(name = "GiamGia")
    private float giamGia;

    @ManyToOne(optional = false)
    @JoinColumn(name = "MaHD", referencedColumnName = "MaHD", nullable = false, insertable = false, updatable = false)
    private HoaDonBanHang hoaDon;

    // Getter, Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getMaKho() {
        return maKho;
    }

    public void setMaKho(String maKho) {
        this.maKho = maKho;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public float getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(float giamGia) {
        this.giamGia = giamGia;
    }

    public HoaDonBanHang getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDonBanHang hoaDon) {
        this.hoaDon = hoaDon;
    }

    public float tinhTien() {
        return (donGia * soLuong) - giamGia;
    }

}
