package iuh.fit.entities;

import jakarta.persistence.*;
import lombok.*;

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
public class PhieuNhapHang {

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

    //check
    @OneToMany(mappedBy = "phieuNhapHang", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<ChiTietSanPham_PhieuNhap> chiTietSanPhamPhieuNhaps = new HashSet<>();
}
