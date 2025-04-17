package iuh.fit;

import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Lớp Preloader cho ứng dụng
 * Hiển thị màn hình splash khi ứng dụng đang khởi động
 */
public class AppPreloader extends Preloader {

    private Stage preloaderStage;
    public static Stage splashStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.preloaderStage = primaryStage;
        splashStage = primaryStage;

        // Thiết lập kiểu stage không có viền
        primaryStage.initStyle(StageStyle.UNDECORATED);

        try {
            // Load màn hình splash
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Splash.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            System.out.println("Splash screen loaded and shown");
        } catch (Exception e) {
            System.err.println("Không thể tải màn hình Splash: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {
        // Ẩn màn hình splash khi ứng dụng chính bắt đầu
        if (info.getType() == StateChangeNotification.Type.BEFORE_START) {
            System.out.println("Main application is about to start, hiding preloader");
            preloaderStage.hide();

            // Đợi một chút trước khi mở giao diện đăng nhập
            // Điều này giúp tránh việc mở đồng thời với App.start()
            Platform.runLater(() -> {
                try {
                    System.out.println("Opening login screen from AppPreloader");
                    App.openLoginGUI();
                } catch (Exception e) {
                    System.err.println("Error opening login screen from AppPreloader: " + e.getMessage());
                    e.printStackTrace();
                }
            });
        }
    }
}
