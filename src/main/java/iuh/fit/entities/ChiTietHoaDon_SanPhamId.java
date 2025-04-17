package iuh.fit.entities;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Embeddable
@EqualsAndHashCode
public class ChiTietHoaDon_SanPhamId implements Serializable {
    private static final long serialVersionUID = 9L; // CHITIETHOADON_SANPHAMID_SERIAL_VERSION_UID
    private String maHD;
    private String maSP;

    public ChiTietHoaDon_SanPhamId(String pnh00123, String sp001) {

    }

    public ChiTietHoaDon_SanPhamId() {

    }
}
