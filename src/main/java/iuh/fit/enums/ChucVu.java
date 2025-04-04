package iuh.fit.enums;

public enum ChucVu {
    Nhan_Vien,
    Nguoi_Quan_Ly;

    public String getChucVu() {
        switch (this) {
            case Nhan_Vien:
                return "Nhan vien";
            case Nguoi_Quan_Ly:
                return "Nguoi quan ly";
            default:
                return null;
        }
    }
}
