package iuh.fit.enums;

public enum PhuongThucThanhToan {
    Tien_Mat,
    Chuyen_Khoan,
    The_Ngan_Hang;

    public String getPhuongThucThanhToan() {
        switch (this) {
            case Tien_Mat:
                return "Tien mat";
            case Chuyen_Khoan:
                return "Chuyen khoan";
            case The_Ngan_Hang:
                return "The ngan hang";
            default:
                return null;
        }
    }
}
