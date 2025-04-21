package iuh.fit.security;

import iuh.fit.entities.TaiKhoan;

/**
 * Security context for managing the current authenticated user
 */
public class SecurityContext {
    private static SecurityContext instance;
    private TaiKhoan currentUser;
    private Role currentRole;

    private SecurityContext() {
        // Private constructor for singleton
    }

    /**
     * Get the singleton instance
     *
     * @return The SecurityContext instance
     */
    public static synchronized SecurityContext getInstance() {
        if (instance == null) {
            instance = new SecurityContext();
        }
        return instance;
    }

    /**
     * Set the current authenticated user and role
     *
     * @param user The authenticated user
     * @param role The user's role
     */
    public void setCurrentUser(TaiKhoan user, Role role) {
        this.currentUser = user;
        this.currentRole = role;
    }

    /**
     * Clear the current authentication context (logout)
     */
    public void clearContext() {
        this.currentUser = null;
        this.currentRole = null;
    }

    /**
     * Get the current authenticated user
     *
     * @return The current user or null if not authenticated
     */
    public TaiKhoan getCurrentUser() {
        return currentUser;
    }

    /**
     * Get the current user's role
     *
     * @return The current role or null if not authenticated
     */
    public Role getCurrentRole() {
        return currentRole;
    }

    /**
     * Check if the current user is authenticated
     *
     * @return true if authenticated, false otherwise
     */
    public boolean isAuthenticated() {
        return currentUser != null;
    }

    /**
     * Check if the current user has a specific permission
     *
     * @param permission The permission to check
     * @return true if the user has the permission, false otherwise
     */
    public boolean hasPermission(Permission permission) {
        if (currentRole == null) {
            return false;
        }
        return RolePermissionManager.shouldHaveAccess(currentRole, permission);
    }
}
