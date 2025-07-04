package baitap.doanjava.entity;

public class NhanVienKho extends Nhanvientong {
    public NhanVienKho(Nhanvientong nv) {
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
        // Kho: lương = lương cơ bản + phụ cấp
        return luongCoBan + getPhuCap();
    }

    @Override
    public double getPhuCap() {
        return 400000;
    }
}
