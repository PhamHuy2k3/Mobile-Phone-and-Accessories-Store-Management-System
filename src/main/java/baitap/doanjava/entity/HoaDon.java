package baitap.doanjava.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class HoaDon {
    @Id
    @Column(name = "MaHD")
    protected String maHD;

    @Column(name = "MaKH")
    protected String maKH;

    @Column(name = "MaNV")
    protected String maNV;

    @Column(name = "NgayLap")
    protected Date ngayLap;

    @Column(name = "TongTien")
    protected float tongTien;

    @Column(name = "MaPTTT")
    protected String maPTTT;

    @Column(name = "TrangThai")
    protected String trangThai;

    // Getter, Setter
    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public String getMaPTTT() {
        return maPTTT;
    }

    public void setMaPTTT(String maPTTT) {
        this.maPTTT = maPTTT;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    // Phương thức trừu tượng để tính tổng tiền
    public abstract float tinhTongTien();
}
