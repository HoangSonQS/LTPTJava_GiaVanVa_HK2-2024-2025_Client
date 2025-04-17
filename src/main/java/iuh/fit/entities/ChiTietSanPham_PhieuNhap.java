package iuh.fit.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "ChiTietSanPham_PhieuNhaps")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class ChiTietSanPham_PhieuNhap implements Serializable {
    private static final long serialVersionUID = 10L; // CHITIETSANPHAM_PHIEUNHAP_SERIAL_VERSION_UID
    @EmbeddedId
    private ChiTietSanPham_PhieuNhapId id;

    @Column(name = "SoLuongSP")
    private int soLuongSP;
    @Column(name ="DonGia")
    private double donGia;

    //check
    @ManyToOne
    @MapsId("maPNH")
    @JoinColumn(name = "MaPNH", nullable = false)
    @ToString.Exclude
    private PhieuNhapHang phieuNhapHang;
    //check
    @ManyToOne
    @MapsId("maSP")
    @JoinColumn(name = "MaSP", nullable = false)
    private SanPham sanPham;
}
