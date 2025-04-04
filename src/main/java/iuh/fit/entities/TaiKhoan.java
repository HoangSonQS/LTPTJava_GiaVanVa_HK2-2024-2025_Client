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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "TaiKhoans")
public class TaiKhoan {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "MaTaiKhoan", nullable = false)
    private String maTaiKhoan;

    @Column(name = "TenDangNhap", nullable = false)
    private String tenDangNhap;
    @Column(name = "MatKhau", nullable = false)
    private String matKhau;
    @Column(name = "ThoiGianDangNhap")
    private LocalDateTime thoiGianDangNhap;

    //check
    @OneToOne
    @JoinColumn(name = "MaNV", nullable = false, unique = true)
    private NhanVien nhanVien;

    //check
    @OneToMany(mappedBy = "taiKhoan", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<CaLam> caLams = new HashSet<>();
}
