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

public class ChiTietSanPham_PhieuNhapId implements Serializable {
    private static final long serialVersionUID = 11L; // CHITIETSANPHAM_PHIEUNHAPID_SERIAL_VERSION_UID
    private String maPNH;
    private String maSP;

    public ChiTietSanPham_PhieuNhapId(String pnh00123, String sp001) {

    }

    public ChiTietSanPham_PhieuNhapId() {

    }
}
