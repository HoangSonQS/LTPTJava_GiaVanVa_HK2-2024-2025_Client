package iuh.fit.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "ChiTietHoaDon_SanPhams")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ChiTietHoaDon_SanPham {

    @EmbeddedId
    private ChiTietHoaDon_SanPhamId id;

    @Column(name = "SoLuongSP", nullable = false)
    private int soLuongSP;

    @Column(name = "DonGia", nullable = false)
    private double donGia;

    //check
    @ManyToOne
    @MapsId("maHD")
    @JoinColumn(name = "MaHD", nullable = false)
    @ToString.Exclude
    private HoaDon hoaDon;
    //check
    @ManyToOne
    @MapsId("maSP")
    @JoinColumn(name = "MaSP", nullable = false)
    private SanPham sanPham;
}