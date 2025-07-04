package baitap.doanjava.entity;

public class NhanVienMoi extends Nhanvientong {
    public NhanVienMoi() {
    }

    @Override
    public double tinhLuong(double luongCoBan, double phuCap) {
        // Nếu chưa xác định vai trò, chỉ trả về lương cơ bản
        return luongCoBan;
    }

    @Override
    public double getPhuCap() {
        // Nhân viên mới chưa có phụ cấp
        return 0;
    }
}
