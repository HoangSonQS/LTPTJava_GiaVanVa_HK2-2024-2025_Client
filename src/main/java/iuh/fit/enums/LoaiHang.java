package iuh.fit.enums;

public enum LoaiHang {
    THUC_PHAM,
    DO_GIA_DUNG,
    THOI_TRANG_VA_PHU_KIEN;

    public String getLoaiHang() {
        switch (this) {
            case THUC_PHAM:
                return "Thuc Pham";
            case DO_GIA_DUNG:
                return "Do Gia Dung";
            case THOI_TRANG_VA_PHU_KIEN:
                return "Thoi Trang Va Phu Kien";
            default:
                return null;
        }
    }
}
