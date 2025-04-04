package iuh.fit.entities;

import iuh.fit.enums.LoaiHang;
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
@Table(name = "SanPhams")
public class SanPham {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "MaSP", nullable = false)
    private String maSP;

    @Column(name = "TenSP", nullable = false)
    private String tenSP;

    @Column(name = "NhaCC", nullable = false)
    private String nhaCC;

    @Column(name = "SoLuongTon")
    private int soLuongTon;

    @Column(name = "GiaNhap")
    private Double giaNhap;

    @Column(name = "GiaBan")
    private Double giaBan;

    @Column(name = "NgaySX")
    private LocalDateTime ngaySX;

    @Column(name = "HanSD")
    private LocalDateTime hanSD;

    @Column(name = "ThoiGianCapNhat")
    private LocalDateTime thoiGianCapNhat;

    @Enumerated(EnumType.STRING)
    @Column(name = "LoaiHang")
    private LoaiHang loaiHang;

    //check
    @OneToMany(mappedBy = "sanPham", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<ChiTietHoaDon_SanPham> chiTietHoaDonSanPhams = new HashSet<>();
    //check
    @OneToMany(mappedBy = "sanPham", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<ChiTietSanPham_PhieuNhap> chiTietSanPhamPhieuNhaps = new HashSet<>();

}