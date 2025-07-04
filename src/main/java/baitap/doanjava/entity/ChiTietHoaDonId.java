package baitap.doanjava.entity;

import java.io.Serializable;
import java.util.Objects;

public class ChiTietHoaDonId implements Serializable {
    private String maHD;
    private String maSP;

    public ChiTietHoaDonId() {
    }

    public ChiTietHoaDonId(String maHD, String maSP) {
        this.maHD = maHD;
        this.maSP = maSP;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ChiTietHoaDonId that = (ChiTietHoaDonId) o;
        return Objects.equals(maHD, that.maHD) && Objects.equals(maSP, that.maSP);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maHD, maSP);
    }
}
