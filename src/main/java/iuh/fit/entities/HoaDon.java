package iuh.fit.entities;

import iuh.fit.enums.PhuongThucThanhToan;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "HoaDons")
public class HoaDon {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "MaHD", nullable = false)
    private String maHD;

    @Column(name = "MaNV", nullable = false, insertable = false, updatable = false)
    private String maNV;

    @Column(name = "MaKH", nullable = true, insertable = false, updatable = false)
    private String maKH;

    @Column(name = "ThoiGian", nullable = false)
    private LocalDateTime thoiGian;

    @Column(name = "TongSoLuongSP")
    private int tongSoLuongSP;

    @Enumerated(EnumType.STRING)
    @Column(name = "PhuongThucTT")
    private PhuongThucThanhToan phuongThucTT;

    @Column(name = "ThanhTien")
    private double thanhTien;


    //check
    @OneToMany(mappedBy = "hoaDon", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<ChiTietHoaDon_SanPham> chiTietHoaDonSanPhams = new HashSet<>();
    //check
    @ManyToOne
    @JoinColumn(name = "MaNV", nullable = false)
    private NhanVien nhanVien;
    //check
    @ManyToOne
    @JoinColumn(name = "MaKH", nullable = false)
    private KhachHang khachHang;
    //check
    @ManyToOne
    @JoinColumn(name = "MaCa", nullable = false)
    private CaLam caLam;
}