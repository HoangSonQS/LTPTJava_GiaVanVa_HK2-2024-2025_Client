package iuh.fit.security;

/**
 * Enum representing permissions in the system
 */
public enum Permission {
    BAN_HANG("Bán hàng"),
    QUAN_LY_SAN_PHAM("Quản lý sản phẩm"),
    QUAN_LY_HOA_DON("Quản lý hoá đơn"),
    QUAN_LY_PHIEU_NHAP("Quản lý phiếu nhập"),
    QUAN_LY_KHACH_HANG("Quản lý khách hàng"),
    QUAN_LY_NHAN_VIEN("Quản lý nhân viên"),
    QUAN_LY_TAI_KHOAN("Quản lý tài khoản"),
    THONG_KE("Thống kê");
    
    private final String displayName;
    
    Permission(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
