package baitap.doanjava.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

// KHÔNG dùng @Entity, @Table ở lớp cha trừu tượng để tránh lỗi JPA
public abstract class Nhanvientong {
    @Id
    @Column(name = "MaNV")
    private String maNV;

    @Column(name = "HoTenNV")
    private String hoTenNV;

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "SoDienThoai")
    private String soDienThoai;

    @Column(name = "VaiTro")
    private String vaiTro;

    @Column(name = "MaVT")
    private Integer maVT;

    @Column(name = "email")
    private String email;

    @Column(name = "hoaHong")
    private Double hoaHong;

    @Column(name = "luong")
    private Double luong;

    public Nhanvientong() {
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTenNV() {
        return hoTenNV;
    }

    public void setHoTenNV(String hoTenNV) {
        this.hoTenNV = hoTenNV;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public Integer getMaVT() {
        return maVT;
    }

    public void setMaVT(Integer maVT) {
        this.maVT = maVT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getHoaHong() {
        return hoaHong;
    }

    public void setHoaHong(Double hoaHong) {
        this.hoaHong = hoaHong;
    }

    public Double getLuong() {
        return luong;
    }

    public void setLuong(Double luong) {
        this.luong = luong;
    }

    public double tinhLuong(double luongCoBan, double phuCap) {
        return luongCoBan;
    }

    // Phương thức trừu tượng lấy phụ cấp
    public abstract double getPhuCap();
    // Kết thúc class Nhanvientong
}
