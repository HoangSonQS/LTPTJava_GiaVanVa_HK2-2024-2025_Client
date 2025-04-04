package iuh.fit.entities;

import iuh.fit.enums.ChucVu;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "NhanViens")
public class NhanVien {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "MaNV", nullable = false)
    private String maNV;

    @Column(name = "TenNV")
    private String tenNV;
    @Column(name = "CCCD")
    private String cccd;
    @Column(name = "DiaChi")
    private String diaChi;
    @Column(name = "Email")
    private String email;
    @Column(name = "Sdt")
    private String sdt;
    @Column(name = "NgaySinh")
    private LocalDate ngaySinh;
    @Column(name = "ChucVu")
    private ChucVu chucVu;

    //check
    @OneToOne(mappedBy = "nhanVien", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private TaiKhoan taiKhoan;

    //check
    @OneToMany(mappedBy = "nhanVien", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<HoaDon> hoaDons = new HashSet<>();

}
