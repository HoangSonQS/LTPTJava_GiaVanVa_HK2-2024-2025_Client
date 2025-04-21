package iuh.fit.security;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Manager class for handling role-permission mappings
 */
public class RolePermissionManager {
    private static final Map<Role, Set<Permission>> ROLE_PERMISSIONS = new HashMap<>();

    static {
        // Initialize permissions for NHAN_VIEN role
        Set<Permission> nhanVienPermissions = new HashSet<>(Arrays.asList(
            Permission.BAN_HANG,
            Permission.QUAN_LY_SAN_PHAM,
            Permission.QUAN_LY_HOA_DON,
            Permission.QUAN_LY_PHIEU_NHAP,
            Permission.QUAN_LY_KHACH_HANG
        ));
        ROLE_PERMISSIONS.put(Role.NHAN_VIEN, nhanVienPermissions);

        // Initialize permissions for QUAN_LY role (includes all permissions)
        Set<Permission> quanLyPermissions = new HashSet<>(Arrays.asList(
            Permission.BAN_HANG,
            Permission.QUAN_LY_SAN_PHAM,
            Permission.QUAN_LY_HOA_DON,
            Permission.QUAN_LY_PHIEU_NHAP,
            Permission.QUAN_LY_KHACH_HANG,
            Permission.QUAN_LY_NHAN_VIEN,
            Permission.QUAN_LY_TAI_KHOAN,
            Permission.THONG_KE
        ));
        ROLE_PERMISSIONS.put(Role.QUAN_LY, quanLyPermissions);
    }

    /**
     * Check if a user with the given role should have access to a specific UI element
     *
     * @param role The user's role
     * @param permission The permission to check
     * @return true if the user should have access, false otherwise
     */
    public static boolean shouldHaveAccess(Role role, Permission permission) {
        // Special case for NHAN_VIEN role - they should have access to all basic features
        if (role == Role.NHAN_VIEN) {
            return permission == Permission.BAN_HANG ||
                   permission == Permission.QUAN_LY_SAN_PHAM ||
                   permission == Permission.QUAN_LY_HOA_DON ||
                   permission == Permission.QUAN_LY_PHIEU_NHAP ||
                   permission == Permission.QUAN_LY_KHACH_HANG;
        }

        // For QUAN_LY role, they have access to everything
        return role == Role.QUAN_LY;
    }

    /**
     * Get permissions for a specific role
     *
     * @param role The role to get permissions for
     * @return Set of permissions for the role
     */
    public static Set<Permission> getPermissionsForRole(Role role) {
        return Collections.unmodifiableSet(ROLE_PERMISSIONS.getOrDefault(role, Collections.emptySet()));
    }

    /**
     * Check if a role has a specific permission
     *
     * @param role The role to check
     * @param permission The permission to check for
     * @return true if the role has the permission, false otherwise
     */
    public static boolean hasPermission(Role role, Permission permission) {
        Set<Permission> permissions = ROLE_PERMISSIONS.get(role);
        return permissions != null && permissions.contains(permission);
    }
}
