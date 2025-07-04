package baitap.doanjava.entity;

public class QuanLy extends Nhanvientong {
    public QuanLy(Nhanvientong nv) {
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
        // Quản lý: lương = lương cơ bản + phụ cấp
        return luongCoBan + getPhuCap();
    }

    @Override
    public double getPhuCap() {
        return 1000000;
    }
}
