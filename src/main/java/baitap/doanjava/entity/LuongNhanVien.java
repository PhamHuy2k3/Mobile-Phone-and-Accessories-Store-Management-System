package baitap.doanjava.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "LuongNhanVien")
public class LuongNhanVien {
    @Id
    @Column(name = "MaLuong")
    private String maLuong;

    @Column(name = "MaNV")
    private String maNV;

    @Column(name = "Thang")
    private String thang;

    @Column(name = "LuongCoBan")
    private Double luongCoBan;

    @Column(name = "PhuCap")
    private Double phuCap;

    @Column(name = "KhauTru")
    private Double khauTru;

    @Column(name = "TongLuong", insertable = false, updatable = false)
    private Double tongLuong;

    public String getMaLuong() {
        return maLuong;
    }

    public void setMaLuong(String maLuong) {
        this.maLuong = maLuong;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }

    public Double getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(Double luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    public Double getPhuCap() {
        return phuCap;
    }

    public void setPhuCap(Double phuCap) {
        this.phuCap = phuCap;
    }

    public Double getKhauTru() {
        return khauTru;
    }

    public void setKhauTru(Double khauTru) {
        this.khauTru = khauTru;
    }

    // Getter cho tongLuong (computed column, không setter hoặc setter rỗng)
    public Double getTongLuong() {
        return tongLuong;
    }

    public void setTongLuong(Double tongLuong) {
        /* do nothing */ }

    // Các hàm tiện ích nếu cần
    public Double tinhTongLuong() {
        if (luongCoBan == null)
            return null;
        double pc = phuCap != null ? phuCap : 0;
        double kt = khauTru != null ? khauTru : 0;
        return luongCoBan + pc - kt;
    }

    public boolean locTheoThang(String thang) {
        return this.thang != null && this.thang.equalsIgnoreCase(thang);
    }

    public boolean locTheoVaiTro(String vaiTroNV) {
        return true;
    }

    public boolean timKiemNhanVien(String tuKhoa) {
        return (maNV != null && maNV.toLowerCase().contains(tuKhoa.toLowerCase()));
    }
}
