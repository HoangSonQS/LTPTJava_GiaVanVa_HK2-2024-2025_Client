# Authentication and Authorization System

This document describes the authentication and authorization system implemented for the application.

## Overview

The system provides role-based access control (RBAC) with two main roles:
- **Nhân viên** (Employee)
- **Quản lý** (Manager)

Each role has specific permissions that determine what features they can access.

## Roles and Permissions

### Nhân viên (Employee) Permissions:
- Bán hàng (Sales)
- Quản lý sản phẩm (Product Management)
- Quản lý hoá đơn (Invoice Management)
- Quản lý phiếu nhập (Import Receipt Management)
- Quản lý khách hàng (Customer Management)

### Quản lý (Manager) Permissions:
- All Employee permissions, plus:
- Quản lý nhân viên (Employee Management)
- Quản lý tài khoản (Account Management)
- Thống kê (Statistics)

## Implementation

The security system consists of the following components:

### 1. Role Enum
Defines the available roles in the system:
```java
public enum Role {
    NHAN_VIEN("Nhân viên"),
    QUAN_LY("Quản lý");
}
```

### 2. Permission Enum
Defines the available permissions in the system:
```java
public enum Permission {
    BAN_HANG("Bán hàng"),
    QUAN_LY_SAN_PHAM("Quản lý sản phẩm"),
    QUAN_LY_HOA_DON("Quản lý hoá đơn"),
    QUAN_LY_PHIEU_NHAP("Quản lý phiếu nhập"),
    QUAN_LY_KHACH_HANG("Quản lý khách hàng"),
    QUAN_LY_NHAN_VIEN("Quản lý nhân viên"),
    QUAN_LY_TAI_KHOAN("Quản lý tài khoản"),
    THONG_KE("Thống kê");
}
```

### 3. Integration with Entity Model
The security system integrates with your existing entity model:

- **TaiKhoan**: Contains user credentials and a reference to NhanVien
- **NhanVien**: Contains employee information including their role (ChucVu)
- **ChucVu**: Enum that defines employee roles (Nhan_Vien, Nguoi_Quan_Ly)

### 4. RolePermissionManager
Maps roles to their respective permissions:
```java
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
}
```

### 5. SecurityContext
Manages the current user's authentication state:
```java
public class SecurityContext {
    private static SecurityContext instance;
    private TaiKhoan currentUser;
    private Role currentRole;

    // Methods to manage authentication state
}
```

### 6. AuthenticationService
Handles user authentication using RMI interfaces:
```java
public class AuthenticationService {
    private final TaiKhoan_interface taiKhoanDAO;

    public boolean authenticate(String username, String password) throws RemoteException {
        // Use the RMI interface to find the user by username and password
        TaiKhoan taiKhoan = taiKhoanDAO.findByUsernameandPassword(username, password);

        if (taiKhoan == null) {
            return false;
        }

        // Determine role based on the user's position (chucVu)
        Role role;
        if (taiKhoan.getNhanVien() != null && taiKhoan.getNhanVien().getChucVu() == ChucVu.Nguoi_Quan_Ly) {
            role = Role.QUAN_LY;
        } else {
            role = Role.NHAN_VIEN;
        }

        // Set the authenticated user in the security context
        SecurityContext.getInstance().setCurrentUser(taiKhoan, role);
        return true;
    }
}
```

### 7. PermissionChecker
Utility class for checking permissions and executing actions based on permissions:
```java
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
}
```

### 8. UIPermissionManager
Utility class for applying permissions to UI elements. When a user doesn't have permission to access a feature, the UI element remains visible but shows a warning message when clicked:
```java
public class UIPermissionManager {
    public static void applyPermission(Node node, Permission permission) {
        if (node == null) {
            return; // Skip if the UI element is null
        }

        SecurityContext securityContext = SecurityContext.getInstance();

        if (!securityContext.isAuthenticated() || !securityContext.hasPermission(permission)) {
            // Keep the node visible but add a click event to show a warning
            node.setOnMouseClicked(event -> {
                showAccessDeniedAlert();
                event.consume();
            });

            // Add a visual indication that this feature is restricted
            node.setOpacity(0.7); // Make it slightly transparent
            node.setStyle("-fx-border-color: #ff9999; -fx-border-width: 1;"); // Add a light red border
        }
    }

    private static void showAccessDeniedAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Quyền truy cập bị từ chối");
        alert.setHeaderText(null);
        alert.setContentText("Bạn không có quyền truy cập chức năng này.");
        alert.showAndWait();
    }
}
```

## Usage

### Authentication with RMI
```java
// Get the TaiKhoan_interface from the RMI registry
System.setProperty("java.security.policy", "rmi.policy");
System.setProperty("java.rmi.server.hostname", "LAPTOP-O8OOBHDK");
java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry("LAPTOP-O8OOBHDK", 9090);
TaiKhoan_interface taiKhoanDao = (TaiKhoan_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/taiKhoanDAO");

// Create the authentication service
AuthenticationService authService = new AuthenticationService(taiKhoanDao);

// Authenticate the user
boolean authenticated = authService.authenticate(username, password);
```

### Checking Permissions
```java
if (PermissionChecker.hasPermission(Permission.QUAN_LY_NHAN_VIEN)) {
    // Perform action that requires employee management permission
}
```

### Applying Permissions to UI Elements
```java
UIPermissionManager.applyPermission(menuItemQLNhanVien, Permission.QUAN_LY_NHAN_VIEN);
UIPermissionManager.applyButtonPermission(btnAddEmployee, Permission.QUAN_LY_NHAN_VIEN);
```

## Integration with Controllers

Each controller should implement permission checks directly in the methods that handle menu clicks and screen transitions. This approach keeps all menu items visible but controls access at the function level. Here's an example from the BanHang_controller:

```java
@FXML
void toQLSanPham(MouseEvent event) {
    // Check permission before proceeding
    PermissionChecker.checkPermissionAndExecute(Permission.QUAN_LY_SAN_PHAM, () -> {
        try {
            // Chuyển đến giao diện quản lý sản phẩm
            loadFXML("/fxml/QL_SanPham_gui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Lỗi", "Không thể mở giao diện quản lý sản phẩm!");
        }
    });
}
```

The `checkPermissionAndExecute` method checks if the user has the required permission and only executes the action if they do. If they don't have permission, it shows a warning message.

## Logout

To log out the current user:
```java
authService.logout();
```
