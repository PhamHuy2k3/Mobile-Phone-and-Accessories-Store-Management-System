package baitap.doanjava.entity;

public abstract class Luong {
    protected String maLuong;
    protected String maNV;
    protected String thang;
    protected Double luongCoBan;
    protected Double phuCap;
    protected Double khauTru;

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

    public abstract Double tinhTongLuong();

    public abstract boolean locTheoThang(String thang);

    public abstract boolean locTheoVaiTro(String vaiTroNV);

    public abstract boolean timKiemNhanVien(String tuKhoa);
}
