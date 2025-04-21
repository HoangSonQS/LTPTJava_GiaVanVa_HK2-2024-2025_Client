package iuh.fit.security;

/**
 * Enum representing user roles in the system
 */
public enum Role {
    NHAN_VIEN("Nhân viên"),
    QUAN_LY("Quản lý");
    
    private final String displayName;
    
    Role(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
