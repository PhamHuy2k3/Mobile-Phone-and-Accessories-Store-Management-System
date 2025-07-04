package baitap.doanjava.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "NhanVien")
public class NhanVien extends Nhanvientong {
    @Id
    @Column(name = "MaNV")
    private String maNV;

    @Column(name = "VaiTro")
    private String vaiTro;

    @Column(name = "HoTenNV")
    private String hoTenNV;

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "SoDienThoai")
    private String soDienThoai;

    @Column(name = "Email")
    private String email;

    public NhanVien() {
        super();
    }

    @Override
    public double getPhuCap() {
        if (vaiTro == null)
            return 0;
        switch (vaiTro.trim().toLowerCase()) {
            case "quản lý":
            case "quan ly":
                return 1000000;
            case "nhân viên bán hàng":
            case "nhan vien ban hang":
                return 500000;
            case "nhân viên kho":
            case "nhan vien kho":
                return 400000;
            default:
                return 0;
        }
    }

    @Override
    public String getMaNV() {
        return maNV;
    }

    @Override
    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
