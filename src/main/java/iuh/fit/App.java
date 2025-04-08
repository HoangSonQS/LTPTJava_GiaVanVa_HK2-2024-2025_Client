package iuh.fit;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

import iuh.fit.daos.HoaDon_dao;
import iuh.fit.daos.SanPham_dao;
import iuh.fit.daos.TaiKhoan_dao;
import iuh.fit.entities.HoaDon;
import iuh.fit.entities.SanPham;
import iuh.fit.entities.TaiKhoan;
import javafx.application.Application;
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
    public static final int widthModalLogin = 600;
    public static final int heightModalLogin = 400;
    public static Stage stage;
    public static Scene primaryScene;
    public static String user;
    public static String ma;
    public static TaiKhoan taiKhoan;

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
        } catch (Exception e) {
            System.err.println("Lỗi khi khởi tạo ứng dụng: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("App.start() called");
        stage = primaryStage;

        // Để đảm bảo rằng splash screen đã được đóng, chúng ta có thể kiểm tra
        if (AppPreloader.splashStage != null && AppPreloader.splashStage.isShowing()) {
            System.out.println("Splash screen is still showing, closing it");
            AppPreloader.splashStage.close();
        }

        // Mở trực tiếp màn hình đăng nhập vì splash screen không hoạt động đúng
        System.out.println("Opening login screen directly from App.start()");
        openLoginGUI();
    }

    /**
     * Mở giao diện chính của ứng dụng
     */
    public static void openMainGUI() throws IOException {
        try {
            Parent root = FXMLLoader.load(App.class.getResource("/fxml/BanHang_gui.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(App.class.getResource("/styles/menu.css").toExternalForm());

            stage.setScene(scene);
            stage.setTitle("Hệ thống quản lý bán hàng");
            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

            // Thiết lập kích thước stage theo màn hình chính
            stage.setX(primaryScreenBounds.getMinX());
            stage.setY(primaryScreenBounds.getMinY());
            stage.setWidth(primaryScreenBounds.getWidth());
            stage.setHeight(primaryScreenBounds.getHeight());
            stage.show();
        } catch (NullPointerException ex) {
            System.err.println("Không tìm thấy file BanHang_gui.fxml: " + ex.getMessage());
            ex.printStackTrace();
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

            if (loginFxmlUrl == null) {
                System.err.println("Login_gui.fxml not found! Trying to open BanHang_gui.fxml instead.");
                openMainGUI();
                return;
            }

            // Load giao diện đăng nhập
            FXMLLoader fxmlLoader = new FXMLLoader(loginFxmlUrl);
            Parent root = fxmlLoader.load();

            // Tạo scene mới
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Đăng nhập");

            // Đặt kích thước và vị trí của cửa sổ
            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primaryScreenBounds.getWidth() - root.prefWidth(-1)) / 2);
            stage.setY((primaryScreenBounds.getHeight() - root.prefHeight(-1)) / 2);

            // Hiển thị cửa sổ
            stage.show();
            System.out.println("Login GUI opened successfully");
        } catch (Exception ex) {
            System.err.println("Error opening Login_gui.fxml: " + ex.getMessage());
            ex.printStackTrace();

            // Nếu không thể mở màn hình đăng nhập, thử mở màn hình chính
            try {
                System.out.println("Falling back to BanHang_gui.fxml");
                openMainGUI();
            } catch (Exception e) {
                System.err.println("Failed to open BanHang_gui.fxml as fallback: " + e.getMessage());
                e.printStackTrace();
                throw new IOException("Failed to open any GUI", ex);
            }
        }
    }

    /**
     * Mở giao diện quên mật khẩu
     */
    public static void openQuenMK() throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/QuenMatKhau_gui.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.setTitle("Quên mật khẩu");
            stage.show();
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
            SanPham_dao sanPhamDao = new SanPham_dao();
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
            HoaDon_dao hoaDonDao = new HoaDon_dao();
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