package baitap.doanjava.entity;

public class NhanVienBanHang extends Nhanvientong {
    public NhanVienBanHang(Nhanvientong nv) {
        this.setMaNV(nv.getMaNV());
        this.setHoTenNV(nv.getHoTenNV());
        this.setDiaChi(nv.getDiaChi());
        this.setSoDienThoai(nv.getSoDienThoai());
        this.setVaiTro(nv.getVaiTro());
        this.setMaVT(nv.getMaVT());
        this.setEmail(nv.getEmail());
        this.setHoaHong(nv.getHoaHong());
    }

    @Override
    public double tinhLuong(double luongCoBan, double phuCap) {
        // Bán hàng: lương = lương cơ bản + phụ cấp + hoa hồng
        double hoaHong = getHoaHong() != null ? getHoaHong().doubleValue() : 0.0;
        return luongCoBan + getPhuCap() + hoaHong;
    }

    @Override
    public double getPhuCap() {
        return 500000;
    }

}
