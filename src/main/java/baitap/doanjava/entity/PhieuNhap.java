package baitap.doanjava.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PhieuNhap")
public class PhieuNhap {
    @Id
    @Column(name = "MaPN")
    private String maPN;

    @Column(name = "MaNCC")
    private String maNCC;

    @Column(name = "MaNV")
    private String maNV;

    @Column(name = "NgayNhap")
    @Temporal(TemporalType.DATE)
    @org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayNhap;

    // Getter & Setter
    public String getMaPN() {
        return maPN;
    }

    public void setMaPN(String maPN) {
        this.maPN = maPN;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    // (Đã xóa thuộc tính tongTien)
}
