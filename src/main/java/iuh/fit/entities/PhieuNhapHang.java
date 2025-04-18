package iuh.fit.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "PhieuNhapHangs")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PhieuNhapHang implements Serializable {
    private static final long serialVersionUID = 6L; // PHIEUNHAPHANG_SERIAL_VERSION_UID

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "MaPNH", nullable = false)
    private String maPNH;

    @Column(name = "MaNV", nullable = false)
    private String maNV;

    @Column(name = "TenNV")
    private String tenNV;
    @Column(name = "ThoiGian")
    private LocalDateTime thoiGian;
    @Column(name = "TongSoLuongSP")
    private int tongSoLuongSP;
    @Column(name = "ThanhTien")
    private double thanhTien;

    public PhieuNhapHang(String maPNH, String maNV, String tenNV, LocalDateTime thoiGian, int tongSoLuongSP, double thanhTien) {
        this.maPNH = maPNH;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.thoiGian = thoiGian;
        this.tongSoLuongSP = tongSoLuongSP;
        this.thanhTien = thanhTien;
    }

    //check
    @OneToMany(mappedBy = "phieuNhapHang", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<ChiTietSanPham_PhieuNhap> chiTietSanPhamPhieuNhaps = new HashSet<>();
}
