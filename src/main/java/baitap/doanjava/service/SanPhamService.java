
// Đặt lại đúng vị trí trong class SanPhamService
package baitap.doanjava.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baitap.doanjava.entity.DienThoai;
import baitap.doanjava.entity.MayTinhBang;
import baitap.doanjava.entity.PhuKien;
import baitap.doanjava.entity.SanPham;
import baitap.doanjava.repository.DienThoaiRepository;
import baitap.doanjava.repository.MayTinhBangRepository;
import baitap.doanjava.repository.PhuKienRepository;

@Service
public class SanPhamService {
    // Sinh mã sản phẩm tự động cho từng loại
    public String generateNextMaSP(String loai) {
        String prefix = "";
        int max = 0;
        if ("dienthoai".equals(loai)) {
            prefix = "DT";
            for (DienThoai dt : getAllDienThoai()) {
                String ma = dt.getMaSP();
                if (ma != null && ma.startsWith(prefix)) {
                    try {
                        int num = Integer.parseInt(ma.substring(prefix.length()));
                        if (num > max)
                            max = num;
                    } catch (Exception ignored) {
                    }
                }
            }
        } else if ("maytinhbang".equals(loai)) {
            prefix = "MTB";
            for (MayTinhBang mtb : getAllMayTinhBang()) {
                String ma = mtb.getMaSP();
                if (ma != null && ma.startsWith(prefix)) {
                    try {
                        int num = Integer.parseInt(ma.substring(prefix.length()));
                        if (num > max)
                            max = num;
                    } catch (Exception ignored) {
                    }
                }
            }
        } else if ("phukien".equals(loai)) {
            prefix = "PK";
            for (PhuKien pk : getAllPhuKien()) {
                String ma = pk.getMaSP();
                if (ma != null && ma.startsWith(prefix)) {
                    try {
                        int num = Integer.parseInt(ma.substring(prefix.length()));
                        if (num > max)
                            max = num;
                    } catch (Exception ignored) {
                    }
                }
            }
        }
        return String.format("%s%03d", prefix, max + 1);
    }

    @Autowired
    private DienThoaiRepository dienThoaiRepository;
    @Autowired
    private MayTinhBangRepository mayTinhBangRepository;
    @Autowired
    private PhuKienRepository phuKienRepository;

    // Lấy tất cả sản phẩm (gộp cả điện thoại, máy tính bảng, phụ kiện)
    public List<SanPham> getAllSanPham() {
        List<SanPham> all = new ArrayList<>();
        all.addAll(getAllDienThoai());
        all.addAll(getAllMayTinhBang());
        all.addAll(getAllPhuKien());
        return all;
    }

    public List<DienThoai> getAllDienThoai() {
        return dienThoaiRepository.findAll().stream()
                .filter(dt -> "LSP1".equals(dt.getMaLoaiSP()))
                .collect(Collectors.toList());
    }

    public List<MayTinhBang> getAllMayTinhBang() {
        return mayTinhBangRepository.findAll().stream()
                .filter(mtb -> "LSP2".equals(mtb.getMaLoaiSP()))
                .collect(Collectors.toList());
    }

    public List<PhuKien> getAllPhuKien() {
        return phuKienRepository.findAll().stream()
                .filter(pk -> "LSP3".equals(pk.getMaLoaiSP()))
                .collect(Collectors.toList());
    }

    public DienThoai getDienThoaiById(String maSP) {
        return dienThoaiRepository.findById(maSP).orElse(null);
    }

    public MayTinhBang getMayTinhBangById(String maSP) {
        return mayTinhBangRepository.findById(maSP).orElse(null);
    }

    public PhuKien getPhuKienById(String maSP) {
        return phuKienRepository.findById(maSP).orElse(null);
    }

    public DienThoai saveDienThoai(DienThoai sp) {
        return dienThoaiRepository.save(sp);
    }

    public MayTinhBang saveMayTinhBang(MayTinhBang sp) {
        return mayTinhBangRepository.save(sp);
    }

    public PhuKien savePhuKien(PhuKien sp) {
        return phuKienRepository.save(sp);
    }

    public void deleteDienThoai(String maSP) {
        dienThoaiRepository.deleteById(maSP);
    }

    public void deleteMayTinhBang(String maSP) {
        mayTinhBangRepository.deleteById(maSP);
    }

    public void deletePhuKien(String maSP) {
        phuKienRepository.deleteById(maSP);
    }

    // Lấy tất cả mã nhà cung cấp từ các loại sản phẩm (không trùng lặp)
    public List<String> getAllMaNCC() {
        Set<String> maNCCSet = new HashSet<>();
        dienThoaiRepository.findAll().forEach(dt -> maNCCSet.add(dt.getMaNCC()));
        mayTinhBangRepository.findAll().forEach(mtb -> maNCCSet.add(mtb.getMaNCC()));
        phuKienRepository.findAll().forEach(pk -> maNCCSet.add(pk.getMaNCC()));
        return new ArrayList<>(maNCCSet);
    }

    // Lấy danh sách điện thoại theo mã nhà cung cấp
    public List<DienThoai> getDienThoaiByNCC(String maNCC) {
        if (maNCC == null || maNCC.equals("tatca"))
            return getAllDienThoai();
        return dienThoaiRepository.findAll().stream()
                .filter(dt -> maNCC.equals(dt.getMaNCC()) && "LSP1".equals(dt.getMaLoaiSP()))
                .collect(Collectors.toList());
    }

    // Lấy danh sách máy tính bảng theo mã nhà cung cấp
    public List<MayTinhBang> getMayTinhBangByNCC(String maNCC) {
        if (maNCC == null || maNCC.equals("tatca"))
            return getAllMayTinhBang();
        return mayTinhBangRepository.findAll().stream()
                .filter(mtb -> maNCC.equals(mtb.getMaNCC()) && "LSP2".equals(mtb.getMaLoaiSP()))
                .collect(Collectors.toList());
    }

    // Lấy danh sách phụ kiện theo mã nhà cung cấp
    public List<PhuKien> getPhuKienByNCC(String maNCC) {
        if (maNCC == null || maNCC.equals("tatca"))
            return getAllPhuKien();
        return phuKienRepository.findAll().stream()
                .filter(pk -> maNCC.equals(pk.getMaNCC()) && "LSP3".equals(pk.getMaLoaiSP()))
                .collect(Collectors.toList());
    }

    // Tìm kiếm nâng cao cho điện thoại
    public List<DienThoai> timKiemDienThoai(String ten, Integer giaTu, Integer giaDen, String maNCC, String loaiSP) {
        return dienThoaiRepository.findAll().stream()
                .filter(dt -> (ten == null || dt.getTenSP().toLowerCase().contains(ten.toLowerCase())))
                .filter(dt -> (giaTu == null || dt.getDonGia() >= giaTu))
                .filter(dt -> (giaDen == null || dt.getDonGia() <= giaDen))
                .filter(dt -> (maNCC == null || maNCC.equals("tatca") || maNCC.equals(dt.getMaNCC())))
                .filter(dt -> (loaiSP == null || loaiSP.equals("tatca") || loaiSP.equals("dienthoai")))
                .filter(dt -> "LSP1".equals(dt.getMaLoaiSP()))
                .collect(Collectors.toList());
    }

    // Tìm kiếm nâng cao cho máy tính bảng
    public List<MayTinhBang> timKiemMayTinhBang(String ten, Integer giaTu, Integer giaDen, String maNCC,
            String loaiSP) {
        return mayTinhBangRepository.findAll().stream()
                .filter(mtb -> (ten == null || mtb.getTenSP().toLowerCase().contains(ten.toLowerCase())))
                .filter(mtb -> (giaTu == null || mtb.getDonGia() >= giaTu))
                .filter(mtb -> (giaDen == null || mtb.getDonGia() <= giaDen))
                .filter(mtb -> (maNCC == null || maNCC.equals("tatca") || maNCC.equals(mtb.getMaNCC())))
                .filter(mtb -> (loaiSP == null || loaiSP.equals("tatca") || loaiSP.equals("maytinhbang")))
                .filter(mtb -> "LSP2".equals(mtb.getMaLoaiSP()))
                .collect(Collectors.toList());
    }

    // Tìm kiếm nâng cao cho phụ kiện
    public List<PhuKien> timKiemPhuKien(String ten, Integer giaTu, Integer giaDen, String maNCC, String loaiSP) {
        return phuKienRepository.findAll().stream()
                .filter(pk -> (ten == null || pk.getTenSP().toLowerCase().contains(ten.toLowerCase())))
                .filter(pk -> (giaTu == null || pk.getDonGia() >= giaTu))
                .filter(pk -> (giaDen == null || pk.getDonGia() <= giaDen))
                .filter(pk -> (maNCC == null || maNCC.equals("tatca") || maNCC.equals(pk.getMaNCC())))
                .filter(pk -> (loaiSP == null || loaiSP.equals("tatca") || loaiSP.equals("phukien")))
                .filter(pk -> "LSP3".equals(pk.getMaLoaiSP()))
                .collect(Collectors.toList());
    }
}
