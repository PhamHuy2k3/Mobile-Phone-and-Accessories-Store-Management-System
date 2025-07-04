package baitap.doanjava.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ChiTietPhieuNhapId implements Serializable {
    @Column(name = "MaPN")
    private String maPN;
    @Column(name = "MaSP")
    private String maSP;
    @Column(name = "MaKho")
    private String maKho;

    public String getMaPN() {
        return maPN;
    }

    public void setMaPN(String maPN) {
        this.maPN = maPN;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getMaKho() {
        return maKho;
    }

    public void setMaKho(String maKho) {
        this.maKho = maKho;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ChiTietPhieuNhapId that = (ChiTietPhieuNhapId) o;
        return Objects.equals(maPN, that.maPN) && Objects.equals(maSP, that.maSP) && Objects.equals(maKho, that.maKho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maPN, maSP, maKho);
    }
}
