package iuh.fit;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

import iuh.fit.interfaces.HoaDon_interface;
import iuh.fit.interfaces.SanPham_interface;
import iuh.fit.interfaces.TaiKhoan_interface;
import iuh.fit.entities.HoaDon;
import iuh.fit.entities.SanPham;
import iuh.fit.entities.TaiKhoan;
import iuh.fit.security.SecurityContext;
import iuh.fit.security.Role;
import iuh.fit.security.Permission;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Lớp chính của ứng dụng
 */
public class App extends Application {
//    public static final int widthModalLogin = 600;
//    public static final int heightModalLogin = 400;
    public static Stage stage;
    public static Scene primaryScene;
    public static String user;
    public static String ma;
    public static TaiKhoan taiKhoan;
    public static String maTraCuu;

    // Biến để theo dõi trạng thái đăng nhập
    private static boolean loginScreenOpened = false;

    public static void main(String[] args) {
        System.setProperty("javafx.preloader", AppPreloader.class.getName());
        launch(args);
    }

    @Override
    public void init() throws Exception {
        try {
            // Kết nối đến cơ sở dữ liệu
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("mariadb");

            // Kiểm tra và cập nhật dữ liệu khi khởi động ứng dụng
            checkSanPham();

            // Initialize the security context
            SecurityContext.getInstance();
        } catch (Exception e) {
            System.err.println("Lỗi khi khởi tạo ứng dụng: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Check if the current user has a specific permission
     *
     * @param permission The permission to check
     * @return true if the user has the permission, false otherwise
     */
    public static boolean hasPermission(Permission permission) {
        SecurityContext securityContext = SecurityContext.getInstance();
        return securityContext.hasPermission(permission);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("App.start() called");
        stage = primaryStage;

        // Thiết lập sự kiện khi đóng cửa sổ chính
        stage.setOnCloseRequest(event -> {
            System.out.println("Application is closing");
            Platform.exit();
            System.exit(0);
        });

        // Chỉ mở giao diện đăng nhập nếu không có splash screen
        if (AppPreloader.splashStage == null) {
            System.out.println("No splash screen detected, checking server connection before opening login screen");
            try {
                System.setProperty("java.security.policy", "rmi.policy");
                System.setProperty("java.rmi.server.hostname", "LAPTOP-O8OOBHDK");
                java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry("LAPTOP-O8OOBHDK", 9090);
                SanPham_interface sanPhamDao = (SanPham_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/sanPhamDAO");
                // Thử gọi một phương thức để kiểm tra kết nối
                sanPhamDao.readAll();
                // Nếu kết nối thành công, mở màn hình đăng nhập
                openLoginGUI();
            } catch (Exception e) {
                System.err.println("Không thể kết nối đến máy chủ: " + e.getMessage());
                e.printStackTrace();
                // Hiển thị thông báo lỗi
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR,
                    "Không thể kết nối đến máy chủ. Vui lòng kiểm tra kết nối và thử lại sau.",
                    javafx.scene.control.ButtonType.OK);
                alert.setTitle("Lỗi kết nối");
                alert.setHeaderText("Không thể kết nối đến máy chủ");
                alert.showAndWait().ifPresent(response -> {
                    if (response == javafx.scene.control.ButtonType.OK) {
                        System.exit(0);
                    }
                });
            }
        }
    }

    /**
     * Mở giao diện chính của ứng dụng
     */
    public static void openMainGUI() throws IOException {
        try {
            // Load the main GUI
            Parent root = FXMLLoader.load(App.class.getResource("/fxml/BanHang_gui.fxml"));
            primaryScene = new Scene(root);  // Khởi tạo primaryScene
            primaryScene.getStylesheets().add(App.class.getResource("/styles/menu.css").toExternalForm());

            // Set the new scene for the stage
            stage.setScene(primaryScene);
            stage.setMaximized(true);
            stage.setTitle("Hệ thống quản lý bán hàng");

            // Show the main GUI
            stage.show();
        } catch (IOException ex) {
            System.err.println("Error loading BanHang_gui.fxml: " + ex.getMessage());
            throw ex;
        }
    }

    /**
     * Mở giao diện đăng nhập
     */
    public static void openLoginGUI() throws IOException {
        try {
            System.out.println("Attempting to open login GUI");

            // Đảm bảo stage đã được khởi tạo
            if (stage == null) {
                System.err.println("Stage is null, creating new stage");
                stage = new Stage();
            }

            // Kiểm tra xem file FXML có tồn tại không
            URL loginFxmlUrl = App.class.getResource("/fxml/Login_gui.fxml");
            System.out.println("Login FXML URL: " + loginFxmlUrl);

//            if (loginFxmlUrl == null) {
//                System.err.println("Login_gui.fxml not found! Trying to open BanHang_gui.fxml instead.");
//                openMainGUI();
//                return;
//            }

            // Load giao diện đăng nhập
            FXMLLoader fxmlLoader = new FXMLLoader(loginFxmlUrl);
            Parent root = fxmlLoader.load();

            // Tạo scene mới
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Đăng nhập");
            stage.setMaximized(true);

            // Đặt kích thước và vị trí của cửa sổ
            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primaryScreenBounds.getWidth() - root.prefWidth(-1)) / 2);
            stage.setY((primaryScreenBounds.getHeight() - root.prefHeight(-1)) / 2);

            // Hiển thị cửa sổ
            stage.show();
            System.out.println(1);
            System.out.println("Login GUI opened successfully");
        } catch (Exception ex) {
            System.err.println("Error opening Login_gui.fxml: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Mở giao diện quên mật khẩu dạng modal dialog
     */
    public static void openQuenMK() throws IOException {
        try {
            // Load the FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/QuenMatKhau_gui.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new stage for the modal dialog
            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL); // Make it modal
            modalStage.initOwner(stage); // Set the owner to the main stage

            // Create and set the scene
            Scene scene = new Scene(root, 600, 400);
            modalStage.setScene(scene);

            // Set the title
            modalStage.setTitle("Quên mật khẩu");

            // Center the dialog on the screen
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            modalStage.setX((screenBounds.getWidth() - scene.getWidth()) / 2);
            modalStage.setY((screenBounds.getHeight() - scene.getHeight()) / 2);

            // Show the dialog and wait for it to be closed
            modalStage.showAndWait();
        } catch (NullPointerException ex) {
            System.err.println("Không tìm thấy file QuenMatKhau_gui.fxml: " + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * Mở cửa sổ modal
     */
    public static void openModal(String fxml) throws IOException {
        Parent root = loadFXML(fxml);
        if (root == null) {
            throw new IllegalArgumentException("Failed to load FXML root for " + fxml);
        }
        Stage modalStage = new Stage();
        Scene scene = new Scene(root);
        modalStage.setScene(scene);
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.setMaximized(true);
        modalStage.showAndWait();
    }

    /**
     * Mở cửa sổ modal với kích thước cụ thể
     */
    public static void openModal(String fxml, int width, int height) throws IOException {
        Parent root = loadFXML(fxml);
        if (root == null) {
            throw new IllegalArgumentException("Failed to load FXML root for " + fxml);
        }
        Stage modalStage = new Stage();
        Scene scene = new Scene(root, width, height);
        modalStage.setScene(scene);
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.showAndWait();
    }

    /**
     * Thay đổi root của scene
     */
    public static void setRoot(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/fxml/" + fxml + ".fxml"));
        System.out.println("Loading FXML: " + loader.getLocation());
        try {
            Parent newRoot = loader.load();
            if (primaryScene != null)
                primaryScene.setRoot(newRoot);
            stage.setScene(primaryScene);
            stage.sizeToScene();
            stage.show();
        } catch (IOException ex) {
            System.err.println("Không tìm thấy file: " + fxml);
            throw ex;
        }
    }

    /**
     * Load FXML file
     */
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Kiểm tra và cập nhật thông tin sản phẩm
     */
    private static void checkSanPham() {
        try {
            System.setProperty("java.security.policy", "rmi.policy");
            System.setProperty("java.rmi.server.hostname", "LAPTOP-O8OOBHDK");
            java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry("LAPTOP-O8OOBHDK", 9090);
            SanPham_interface sanPhamDao = (SanPham_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/sanPhamDAO");
            List<SanPham> dsSanPham = sanPhamDao.readAll();

            // Kiểm tra và cập nhật thông tin sản phẩm nếu cần
            LocalDateTime now = LocalDateTime.now();

            for (SanPham sp : dsSanPham) {
                // Kiểm tra hạn sử dụng
                if (sp.getHanSD() != null && now.isAfter(sp.getHanSD())) {
                    // Cập nhật trạng thái sản phẩm hết hạn nếu cần
                    System.out.println("Sản phẩm " + sp.getMaSP() + " đã hết hạn sử dụng");
                }

                // Cập nhật thời gian cập nhật
                sp.setThoiGianCapNhat(now);
                sanPhamDao.update(sp);
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi kiểm tra sản phẩm: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Kiểm tra và cập nhật thông tin hóa đơn
     */
    private static void checkHoaDon() {
        try {
            System.setProperty("java.security.policy", "rmi.policy");
            System.setProperty("java.rmi.server.hostname", "LAPTOP-O8OOBHDK");
            java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.getRegistry("LAPTOP-O8OOBHDK", 9090);
            HoaDon_interface hoaDonDao = (HoaDon_interface) registry.lookup("rmi://LAPTOP-O8OOBHDK:9090/hoaDonDAO");
            List<HoaDon> dsHoaDon = hoaDonDao.readAll();

            // Kiểm tra và cập nhật thông tin hóa đơn nếu cần
            LocalDateTime now = LocalDateTime.now();

            for (HoaDon hd : dsHoaDon) {
                // Thực hiện các kiểm tra và cập nhật cần thiết
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi kiểm tra hóa đơn: " + e.getMessage());
            e.printStackTrace();
        }
    }
}