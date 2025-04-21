package iuh.fit.security;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Utility class for checking permissions
 */
public class PermissionChecker {

    /**
     * Check if the current user has the required permission
     *
     * @param permission The permission to check
     * @return true if the user has the permission, false otherwise
     */
    public static boolean hasPermission(Permission permission) {
        SecurityContext securityContext = SecurityContext.getInstance();

        if (!securityContext.isAuthenticated()) {
            showAccessDeniedAlert("Bạn chưa đăng nhập");
            return false;
        }

        if (!securityContext.hasPermission(permission)) {
            showAccessDeniedAlert("Bạn không có quyền truy cập chức năng này");
            return false;
        }

        return true;
    }

    /**
     * Check permission and execute an action if the user has permission
     *
     * @param permission The permission to check
     * @param action The action to execute if the user has permission
     */
    public static void checkPermissionAndExecute(Permission permission, Runnable action) {
        if (hasPermission(permission)) {
            action.run();
        }
    }

    /**
     * Show an access denied alert
     *
     * @param message The message to display
     */
    private static void showAccessDeniedAlert(String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Từ chối truy cập");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
