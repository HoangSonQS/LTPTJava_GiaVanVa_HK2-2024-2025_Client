package iuh.fit.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "KhachHangs")
public class KhachHang {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "MaKH", nullable = false)
    private String maKH;

    @Column(name = "TenKH", nullable = false)
    private String tenKH;

    @Column(name = "SDT", nullable = false)
    private String sdt;

    //check
    @OneToMany(mappedBy = "khachHang", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<HoaDon> hoaDons = new HashSet<>();
}
