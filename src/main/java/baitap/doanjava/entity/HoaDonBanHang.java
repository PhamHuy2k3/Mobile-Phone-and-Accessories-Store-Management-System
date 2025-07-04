package baitap.doanjava.entity;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
@Table(name = "HoaDon")
public class HoaDonBanHang extends HoaDon {

    @OneToMany(mappedBy = "hoaDon", cascade = CascadeType.ALL)
    private List<ChiTietHoaDon> chiTietHoaDonList;

    @Override
    public float tinhTongTien() {
        float tong = 0f;
        if (chiTietHoaDonList != null) {
            for (ChiTietHoaDon ct : chiTietHoaDonList) {
                tong += ct.tinhTien();
            }
        }
        return tong;
    }

    public List<ChiTietHoaDon> getChiTietHoaDonList() {
        return chiTietHoaDonList;
    }

    public void setChiTietHoaDonList(List<ChiTietHoaDon> chiTietHoaDonList) {
        this.chiTietHoaDonList = chiTietHoaDonList;
    }
}
